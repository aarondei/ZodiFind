package cit.edu.zodifind.wheel_of_fortune

import android.content.res.Resources
import android.graphics.Color
import android.util.TypedValue
import androidx.core.graphics.ColorUtils
import kotlin.math.roundToInt


fun Float.spToPx(): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        Resources.getSystem().displayMetrics
    )
}

fun Float.dpToPx(): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )
}

fun Float.roundDpToPx(): Int {
    return dpToPx().roundToInt()
}

fun getTextColor(color: Int): Int {
    return if (ColorUtils.calculateLuminance(color) > 0.5) {
        Color.BLACK // If it's a light color
    } else {
        Color.WHITE // If it's a dark color
    }
}