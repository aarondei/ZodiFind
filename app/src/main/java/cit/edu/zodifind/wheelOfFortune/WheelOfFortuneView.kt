package cit.edu.zodifind.wheelOfFortune

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Typeface
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.Px
import androidx.annotation.RequiresApi
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FlingAnimation
import androidx.dynamicanimation.animation.FloatValueHolder
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import cit.edu.zodifind.R
import cit.edu.zodifind.wheelFragments.drawPointerPath
import kotlin.math.absoluteValue
import kotlin.math.max


@RequiresApi(Build.VERSION_CODES.O) // added annotation
class WheelOfFortuneView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : View(context, attrs), RotationGestureDetector.OnRotationGestureListener {

    companion object {
        const val DEFAULT_WHEEL_BITMAP_SIZE = 1080
    }

    private val paint = Paint().apply {
        isAntiAlias = false
    }
    private val pointerPaint = Paint()
    private var bitmapScale: Float? = null

    private var pointerPath: Path? = null // fixme убрать когда-нибудь

    private val wheel = WheelOfFortune(context)

    @Px
    var fixedBitmapSize: Int? = null
        set(value) {
            field = value
            requestLayout()
        }

    @Px
    var strokeWidth = wheel.strokeWidth
        set(value) {
            wheel.strokeWidth = value
            field = value
        }

    @ColorInt
    var strokeColor = wheel.strokeColor
        set(value) {
            wheel.strokeColor = value
            field = value
        }

    @Px
    var size: Int? = null
        private set(value) {
            field = value
        }


    private val springForce = SpringForce(166f).apply {
        dampingRatio = SpringForce.DAMPING_RATIO_NO_BOUNCY
        stiffness = 10f
    }
    private val animatedValue = FloatValueHolder(0f)

    private val mRotationDetector: RotationGestureDetector = RotationGestureDetector(animatedValue,this);
    @ColorInt
    private var currentColor = Color.TRANSPARENT

    private var spinCount = 0

    private val fling = FlingAnimation(animatedValue).apply {
        minimumVisibleChange = SpringAnimation.MIN_VISIBLE_CHANGE_ROTATION_DEGREES
        addUpdateListener { _, value, _ ->
            spinCount = (value.absoluteValue/360f).toInt()
            currentColor = if (value > 0)
                (value - (360f * spinCount)).getColor()
            else
                (360f - (value.absoluteValue - (360f * spinCount))).getColor()
            invalidate()
        }
        addEndListener { _, _, _, _ ->
            spinCount = (animatedValue.value.absoluteValue/360f).toInt()
            currentColor = if (animatedValue.value > 0)
                (animatedValue.value - (360f * spinCount)).getColor()
            else
                (360f - (animatedValue.value.absoluteValue - (360f * spinCount))).getColor()
        }
    }
    private val animation = SpringAnimation(animatedValue).apply {
        spring = springForce
        minimumVisibleChange = SpringAnimation.MIN_VISIBLE_CHANGE_ROTATION_DEGREES
        addUpdateListener { _, value, _ ->
            currentColor = if (value > 0)
                (value - (360f * spinCount)).getColor()
            else
                (360f - (value.absoluteValue - (360f * spinCount))).getColor()
            invalidate()
        }
        addEndListener { _, _, _, _ ->
            currentColor = if (animatedValue.value > 0)
                (animatedValue.value - (360f * spinCount)).getColor()
            else
                (360f - (animatedValue.value.absoluteValue - (360f * spinCount))).getColor()
            animatedValue.value = springForce.finalPosition - (360f * spinCount)
        }
    }

    var items: List<WheelItem> = listOf()
        set(value) {
            field = value
            if (wheel.bitmap != null) wheel.setItems(value)
            invalidate()
        }


    init {
        overScrollMode = OVER_SCROLL_NEVER
        attrs?.run {
            val attrsArray = context.obtainStyledAttributes(attrs, R.styleable.WheelOfFortuneView)
            val typeface = attrsArray.getFont(R.styleable.WheelOfFortuneView_fontFamily)
            val attrStrokeWidth =
                attrsArray.getDimension(R.styleable.WheelOfFortuneView_strokeWidth, 0f)
            val attrStrokeColor =
                attrsArray.getColor(R.styleable.WheelOfFortuneView_strokeColor, Color.BLACK)
            if (attrsArray.getBoolean(
                    R.styleable.WheelOfFortuneView_fixedWheelBitmapSize,
                    true
                ) && fixedBitmapSize == null
            ) fixedBitmapSize = DEFAULT_WHEEL_BITMAP_SIZE
            typeface?.run { wheel.setFont(this) }
            strokeWidth = attrStrokeWidth
            strokeColor = attrStrokeColor
            attrsArray.recycle()
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        size = max(w, h)
        val bitmapSize = fixedBitmapSize ?: size!!
        wheel.prepareBitmap(bitmapSize)
        wheel.setItems(items)
        if (fixedBitmapSize != null) bitmapScale = size!!.toFloat() / wheel.size.toFloat()

        pointerPath = drawPointerPath(size!!)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (items.isNotEmpty()) {
            canvas.drawWithLayer {
                rotate(animatedValue.value, size!! / 2f, size!! / 2f)
                bitmapScale?.let { scale(it, it) }
                wheel.bitmap?.let { drawBitmap(it, 0f, 0f, paint) }
            }
            canvas.drawPointer()
        }
    }

    private fun Canvas.drawPointer() {
        drawWithLayer {
            pointerPath?.let {
                drawPath(it, pointerPaint.apply {
                    color = Color.BLACK
                })
                scale(0.8f, 0.8f, size!! / 2f, 0f)
                drawPath(
                    it,
                    pointerPaint.apply {
                        color = currentColor
                        strokeWidth = 10f
                        style = Paint.Style.FILL
                    })
            }
        }
    }

    private fun Float.getColor(): Int {
        wheel.items.forEach {
            if (
                this@getColor in it.startAngle.absoluteValue..it.endAngle.absoluteValue
                || this@getColor in it.startAngle..it.endAngle
            ) return it.color
        }
        return Color.TRANSPARENT
    }

    fun animTest() {
        if (!animation.isRunning) {
            val targetValue = animatedValue.value + (540..(360 * 15)).random()
            springForce.finalPosition = targetValue
            spinCount = (targetValue.absoluteValue / 360f).toInt()
            animation.start()
        } else
            animation.skipToEnd()
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let { mRotationDetector.onTouchEvent(event, width/2f) }
        return true
    }

    override fun onStartRotation() {
        val currentValue = animatedValue.value
        animation.cancel()
        fling.cancel()
        animatedValue.value = currentValue
        invalidate()
    }

    override fun onRotation(angleDelta: Float) {
        angleDelta.let {
            animatedValue.value += it
            spinCount = (animatedValue.value.absoluteValue/360f).toInt()
            currentColor = if (animatedValue.value > 0)
                (animatedValue.value - (360f * spinCount)).getColor()
            else
                (360f - (animatedValue.value.absoluteValue - (360f * spinCount))).getColor()
            Log.e("ANGLE", animatedValue.value.toString())
            invalidate()
        }
    }

    override fun onFling(velocity: Float) {
        Log.e("FLING", velocity.toString())
        fling
            .setFriction(0.15f)
            .setStartVelocity(velocity)
            .start()
    }

    fun setFont(typeface: Typeface) = wheel.setFont(typeface)

    fun getWheelBitmap() = wheel.bitmap?.copy(Bitmap.Config.ARGB_8888, false)
}