package cit.edu.zodifind.wheelOfFortune.measurements

import kotlin.math.acos
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Measure available width
 */
fun measureWidth(sideWidth: Float, height: Float): Float {
    return sqrt(sideWidth.pow(2) - height.pow(2)) * 2f
}

/**
 * @param offset distance from the edge of the circle to the point closest to the center of the circle
 * @return true if out of bounds
 */
fun checkOutOfBounds(
    offset: Float,
    layoutWidth: Float,
    sweepAngle: Float
): Boolean {
    val leg = layoutWidth / 2
    val triangleSide = sqrt(offset.pow(2) + leg.pow(2))
    val angle = measureAngle(triangleSide, triangleSide, layoutWidth)
    return sweepAngle <= angle
}

/**
 * measure the angle of a triangle
 *
 * A,B,C - width of triangle sides
 * @return angle AB
 */
fun measureAngle(a: Float, b: Float, c: Float): Double {
    val cosAB = ((a.pow(2) + b.pow(2)) - c.pow(2)) / (2 * a * b)
    return Math.toDegrees(acos(cosAB).toDouble())
}