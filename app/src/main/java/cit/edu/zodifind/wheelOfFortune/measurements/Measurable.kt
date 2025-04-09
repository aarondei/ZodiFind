package cit.edu.zodifind.wheelOfFortune.measurements

import android.graphics.Rect
import cit.edu.zodifind.wheelOfFortune.dpToPx
import cit.edu.zodifind.wheelOfFortune.roundDpToPx
import cit.edu.zodifind.wheelOfFortune.spToPx
import kotlin.math.roundToInt

interface Measurable {
    fun measure(wheelSize: Int): Float
}

class Dp(private val value: Float) : Measurable {
    constructor(value: Int) : this(value.toFloat())

    override fun measure(wheelSize: Int): Float = value.dpToPx()
}

class Sp(private val value: Float) : Measurable {
    constructor(value: Int) : this(value.toFloat())

    override fun measure(wheelSize: Int): Float = value.spToPx()
}

class Px(private val value: Float) : Measurable {
    constructor(value: Int) : this(value.toFloat())

    override fun measure(wheelSize: Int): Float = value
}

class Fraction(private val value: Float) : Measurable {
    override fun measure(wheelSize: Int): Float = wheelSize * value
}

interface MeasurableRect {
    fun measure(wheelSize: Int): Rect
}

class DpSize(val width: Float, val height: Float) : MeasurableRect {
    constructor(width: Int, height: Int) : this(width.toFloat(), height.toFloat())

    override fun measure(wheelSize: Int): Rect = Rect(
        0,
        0,
        width.roundDpToPx(),
        height.roundDpToPx()
    )
}

class PxSize(val width: Float, val height: Float) : MeasurableRect {
    constructor(width: Int, height: Int) : this(width.toFloat(), height.toFloat())

    override fun measure(wheelSize: Int): Rect = Rect(
        0,
        0,
        width.roundToInt(),
        height.roundToInt()
    )
}

class FractionSize(val width: Float, val height: Float) : MeasurableRect {
    override fun measure(wheelSize: Int): Rect = Rect(
        0,
        0,
        (wheelSize * width).roundToInt(),
        (wheelSize * height).roundToInt()
    )
}

fun Float.asDp() = Dp(this)

fun Int.asDp() = toFloat().asDp()

fun Float.asSp() = Sp(this)

fun Int.asSp() = toFloat().asSp()

fun Float.asPx() = Px(this)

fun Int.asPx() = toFloat().asPx()

fun Float.asFraction() = Fraction(this)