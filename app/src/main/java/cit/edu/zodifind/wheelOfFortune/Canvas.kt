package cit.edu.zodifind.wheelOfFortune

import android.graphics.Canvas
import android.graphics.Paint

fun Canvas.drawWithLayer(
    left:Float = 0f,
    top:Float = 0f,
    right:Float = width.toFloat(),
    bottom:Float = height.toFloat(),
    paint: Paint? = null,
    block: Canvas.() -> Unit) {
    val checkpoint = saveLayer(left, top, right, bottom, paint)
    block()
    restoreToCount(checkpoint)
}