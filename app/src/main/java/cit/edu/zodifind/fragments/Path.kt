package cit.edu.zodifind.fragments

import android.graphics.Path
import android.util.Size
import kotlin.math.roundToInt


fun drawPointerPath(
    wheelSize: Int,
    pointerSize: Size = Size(
        (wheelSize * 0.075f).roundToInt(),
        (wheelSize * 0.075f).roundToInt()
    )
): Path {
    val center = wheelSize / 2f
    val startX = center - pointerSize.centerX()
    val path = Path().apply {
        moveTo(startX, 0f)
        lineTo(startX + pointerSize.width, 0f)
        lineTo(center, pointerSize.height.toFloat())
        close()

    }
    path.op(roundPath(wheelSize.toSize(), center), Path.Op.INTERSECT)
    return path
}

fun roundPath(size: Size, radius: Float) = Path().apply {
    addCircle(size.centerX(), size.centerY(), radius, Path.Direction.CW)
}

fun Int.toSize() = Size(this,this)

fun Size.centerX() = width / 2f

fun Size.centerY() = height / 2f