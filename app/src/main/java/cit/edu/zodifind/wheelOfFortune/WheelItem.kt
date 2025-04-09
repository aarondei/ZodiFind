package cit.edu.zodifind.wheelOfFortune

import android.graphics.Rect
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import cit.edu.zodifind.wheelOfFortune.measurements.Fraction
import cit.edu.zodifind.wheelOfFortune.measurements.FractionSize
import cit.edu.zodifind.wheelOfFortune.measurements.Measurable
import cit.edu.zodifind.wheelOfFortune.measurements.MeasurableRect

data class WheelItem(
    val text: String? = null,
    val weight: Int,
    @ColorInt val color: Int,
    @DrawableRes val icon: Int? = null,
    val direction: ItemDirection = ItemDirection.ACROSS,
    val textSize: Measurable = Fraction(.03f),
    @ColorInt val textColor: Int? = null,
    val iconSize: MeasurableRect = FractionSize(0.05f, 0.05f),
    val edgePadding: Measurable = Fraction(0.05f),
    val centerPadding: Measurable = Fraction(0.1f),
    val spacing: Measurable? = null
) {
    internal fun measureItem(
        wheelSize: Int,
        startAngle: Float,
        sweepAngle: Float
    ): MeasuredItem = MeasuredItem(
        text,
        weight,
        color,
        icon,
        direction,
        if (!text.isNullOrBlank()) textSize.measure(wheelSize) else null,
        textColor ?: getTextColor(color),
        if (icon != null) iconSize.measure(wheelSize) else null,
        edgePadding.measure(wheelSize),
        centerPadding.measure(wheelSize),
        spacing?.measure(wheelSize) ?: 0f,
        startAngle,
        sweepAngle
    )
}

data class MeasuredItem internal constructor(
    val text: String? = null,
    val value: Int,
    @ColorInt val color: Int,
    @DrawableRes val icon: Int? = null,
    val direction: ItemDirection,
    val textSize: Float? = null,
    @ColorInt val textColor: Int,
    val iconRect: Rect? = null,
    val edgePadding: Float,
    val centerPadding: Float,
    val spacing: Float,
    val startAngle: Float,
    val sweepAngle: Float,
    val endAngle: Float = startAngle + sweepAngle
)

enum class ItemDirection {
    ALONG, ACROSS
}