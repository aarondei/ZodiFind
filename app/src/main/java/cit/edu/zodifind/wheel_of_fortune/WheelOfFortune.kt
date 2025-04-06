package cit.edu.zodifind.wheel_of_fortune

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import android.graphics.Typeface
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.Log
import androidx.core.content.res.ResourcesCompat
import cit.edu.zodifind.wheel_of_fortune.measurements.checkOutOfBounds
import cit.edu.zodifind.wheel_of_fortune.measurements.measureText
import cit.edu.zodifind.wheel_of_fortune.measurements.measureWidth
import kotlin.math.absoluteValue
import kotlin.math.roundToInt


class WheelOfFortune(
    private val context: Context
) {

    private val porterDuffXfermode = PorterDuffXfermode(PorterDuff.Mode.DST_ATOP)
    private val paint = Paint()
    private val arcPaint = Paint().apply {
        style = Paint.Style.FILL
        isAntiAlias = true
    }
    private val textPaint = TextPaint().apply {
        color = Color.BLACK
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    var strokeWidth = 2.5f
        set(value) {
            field = value
            canvas?.drawWheel()
        }
    var strokeColor = Color.BLACK
        set(value) {
            field = value
            canvas?.drawWheel()
        }
    var center = 0f
        private set
    var size = 0
        private set(value) {
            center = value / 2f
            field = value
        }


    private var canvas: Canvas? = null
    var bitmap: Bitmap? = null
        private set(value) {
            field = value
        }


    var totalValue = 0
        private set
    var items: List<MeasuredItem> = listOf()
        private set


    private fun Canvas.drawWheel() = drawWithLayer {
        Log.e("TAGTAG DNST", context.resources.displayMetrics.density.toString())
        if (items.size == 1) rotate(-180f, center, center)
        items.forEach {
            drawWithLayer {
                drawItemContent(it)
                drawItemArc(it)
            }
        }
    }

    private fun Canvas.drawItemArc(item: MeasuredItem) {
        drawWithLayer(paint = paint.apply { xfermode = porterDuffXfermode }) {
            canvas?.rotate(-90f, center, center)
            drawArc(
                RectF(0f, 0f, size.toFloat(), size.toFloat()),
                item.startAngle,
                item.sweepAngle,
                true,
                arcPaint.apply {
                    color = item.color
                    style = Paint.Style.FILL
                }
            )
            val padding = strokeWidth / 2f
            drawArc(
                RectF(padding, padding, size - padding, size - padding),
                item.startAngle,
                item.sweepAngle,
                true,
                arcPaint.apply {
                    color = strokeColor
                    strokeWidth = this@WheelOfFortune.strokeWidth
                    style = Paint.Style.STROKE
                }
            )
        }
    }

    private fun Canvas.drawItemContent(item: MeasuredItem) {
        if (!checkOutOfBounds(
                center - ((item.iconRect?.height() ?: 0) + item.edgePadding),
                item.iconRect?.width()?.toFloat() ?: 0f,
                item.sweepAngle.absoluteValue
            ) || item.icon == null
        ) drawWithLayer {
            rotate(item.startAngle + (item.sweepAngle / 2f), center, center)
            if (item.direction == ItemDirection.ACROSS && item.icon != null && item.iconRect != null) {
                drawWithLayer { // todo перенести в отдельную функцию
                    ResourcesCompat.getDrawable(context.resources, item.icon, context.theme)
                        ?.apply {
                            bounds = item.iconRect
                            translate(center - (item.iconRect.height() / 2f), item.edgePadding)
                            draw(this@drawItemContent)
                        }
                }
            }

            if (!item.text.isNullOrBlank())
                drawItemText(item)
        }
    }


    private fun Canvas.drawItemText(item: MeasuredItem) {
        val isAcross = item.direction == ItemDirection.ACROSS
        val edgeOffset =
            if (item.icon != null) (item.iconRect?.height() ?: 0) + item.spacing + item.edgePadding
            else item.edgePadding

        val textLayoutWidth = (if (isAcross)
            measureWidth(center, center - edgeOffset)
        else
            (center - (item.edgePadding + item.centerPadding))).coerceAtLeast(0f).roundToInt()

        var staticLayout: StaticLayout? = measureText(
            text = item.text!!,
            paint = textPaint.apply {
                color = item.textColor
                textSize = item.textSize ?: 0f
            },
            width = textLayoutWidth,
            align = if (isAcross) Layout.Alignment.ALIGN_CENTER else Layout.Alignment.ALIGN_NORMAL,
            maxLines = if (isAcross) Int.MAX_VALUE else 1
        )


        val outOfBound = if (isAcross) {
            staticLayout =
                staticLayout?.checkLinesOutOfBound(
                    item,
                    textLayoutWidth,
                    edgeOffset
                )
            staticLayout == null
        } else checkOutOfBounds(
            center - (staticLayout!!.width + item.edgePadding),
            (staticLayout.getLineBaseline(0)).toFloat(),
            item.sweepAngle.absoluteValue
        )

        if (!outOfBound)
            drawWithLayer {
                staticLayout!!
                if (isAcross)
                    translate(center - (staticLayout.width / 2f), edgeOffset)
                else {
                    rotate(-90f, center, 0f)
                    translate(
                        center - (staticLayout.width + item.edgePadding),
                        -(staticLayout.height / 2f)
                    )
                }
                staticLayout.draw(this)
            }
    }

    private fun StaticLayout.checkLinesOutOfBound(
        item: MeasuredItem,
        textLayoutWidth: Int,
        edgeOffset: Float
    ): StaticLayout? {
        var maxLines: Int? = null
        if (item.sweepAngle.absoluteValue >= 180f) {
            val availableHeight = center - edgeOffset
            if (height <= availableHeight) return this
            maxLines = getLineForVertical(availableHeight.roundToInt())
        } else for (i in 0..<lineCount) {
            val lineWidth = getLineWidth(i)
            val lineHeight = getLineBottom(i)
            if (
                checkOutOfBounds(
                    (center - (lineHeight + edgeOffset)).coerceAtLeast(0f),
                    lineWidth,
                    item.sweepAngle.absoluteValue
                )
            ) break
            maxLines = i + 1
        }
        if (maxLines == lineCount) return this
        return if (maxLines != null) measureText(
            text = item.text!!,
            paint = textPaint.apply {
                color = item.textColor
                textSize = item.textSize ?: 0f
            },
            width = textLayoutWidth,
            align = Layout.Alignment.ALIGN_CENTER,
            maxLines = maxLines
        ) else null
    }


    private fun Int.toSweepAngle(reversed: Boolean = false): Float {
        val sweep = (this.toDouble() / totalValue.toDouble()) * 360.0
        return (if (reversed) -sweep else sweep).toFloat()
    }

    fun setFont(typeface: Typeface) {
        textPaint.typeface = typeface
    }

    fun prepareBitmap(size: Int) {
        require(size > 0) { "bitmap size must be > 0" }
        this.size = size
        bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        canvas = Canvas(bitmap!!)
        if (items.isNotEmpty())
            canvas?.drawWheel()
    }

    /**
     * @throws IllegalStateException if the bitmap was not created
     */
    fun setItems(items: List<WheelItem>) {
        if (bitmap == null) throw IllegalStateException("create a bitmap before setting items")
        totalValue = items.sumOf { it.weight }
        var startAngle = 0f
        this.items = items.map {
            val sweepAngle = it.weight.toSweepAngle(true)
            val measuredItem = it.measureItem(size, startAngle, sweepAngle)
            startAngle += sweepAngle
            measuredItem
        }
        Log.e("TAGTAG",this.items.toString())
        canvas?.drawWheel()
    }
}