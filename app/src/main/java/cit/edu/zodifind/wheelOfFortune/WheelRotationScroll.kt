package cit.edu.zodifind.wheelOfFortune

import android.view.MotionEvent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.dynamicanimation.animation.FloatValueHolder
import java.lang.Math.toDegrees
import kotlin.math.absoluteValue
import kotlin.math.atan2

class RotationGestureDetector(
    private val valueHolder: FloatValueHolder? = null,
    private val mListener: OnRotationGestureListener
) {

    private var prevAngle: Float = 0f
    private var angle = 0f
    private var velocityTracker: VelocityTracker? = null

    fun onTouchEvent(event: MotionEvent, center: Float) {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                mListener.onStartRotation()
                if (velocityTracker == null && valueHolder != null) velocityTracker = VelocityTracker()
                prevAngle = getAngle(center, center, event.x, event.y)
                velocityTracker?.resetTracking()
                velocityTracker?.addPosition(event.eventTime,Offset(valueHolder!!.value,0f))
            }

            MotionEvent.ACTION_MOVE -> {
                angle = getAngle(center, center, event.x, event.y)
                val delta = angle - prevAngle
                if (delta.absoluteValue < 180f) {
                    mListener.onRotation(delta)
                    velocityTracker?.addPosition(event.eventTime, Offset(valueHolder!!.value, 0f))
                }
                prevAngle = angle
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                velocityTracker?.addPosition(event.eventTime,Offset(valueHolder!!.value, 0f))
                val velocity = velocityTracker?.calculateVelocity()?.x ?: 0f
                mListener.onFling(velocity)
                prevAngle = 0f
                velocityTracker?.resetTracking()
                velocityTracker = null
            }
        }
    }

    private fun getAngle(
        nfX: Float,
        nfY: Float,
        nsX: Float,
        nsY: Float
    ): Float {
        val a = atan2((nfY - nsY).toDouble(), (nfX - nsX).toDouble()).toFloat()
        val angle = toDegrees(a.toDouble()).toFloat() % 360
        return angle
    }

    interface OnRotationGestureListener {
        fun onStartRotation()
        fun onRotation(angleDelta: Float)
        fun onFling(velocity: Float)
    }
}