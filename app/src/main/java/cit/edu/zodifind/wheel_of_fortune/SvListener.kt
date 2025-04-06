package cit.edu.zodifind.wheel_of_fortune

import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener

/** Class to be attached to views which will have their values updated by circular scrolling
 */
internal class SvListener(private val mStrategy: SvStrategy, private val mScale: Float) :
    OnTouchListener {
    private val x = FloatArray(3)
    private val y = FloatArray(3)
    private var cycler = 0
    private var haveFullSet = false
    private var mCurrGrads = 0
    private var degs = 0.0f
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        if (event.actionMasked == MotionEvent.ACTION_DOWN) {
            cycler = 0
            x[cycler] = event.x
            y[cycler] = event.y
            haveFullSet = false
            mCurrGrads = 0
            degs = 0.0f
            event.x
            event.y
            mStrategy.onStart(v)
        }
        if (event.actionMasked == MotionEvent.ACTION_MOVE) {
            handleMove(v, event)
        }
        if (event.actionMasked == MotionEvent.ACTION_UP) {
            cycler = 0
            haveFullSet = false
            mStrategy.onFinish(v)
        }
        return true
    }

    private fun handleMove(v: View, event: MotionEvent) {

        // The distance of this touch from the last recorded point
        val lastPointDist = mag(event.x - x[cycler], event.y - y[cycler]) / mScale
        if (lastPointDist < POINT_DIST) {
            return
        }
        cycler = (cycler + 1) % 3
        x[cycler] = event.x
        y[cycler] = event.y
        if (!haveFullSet) {
            if (cycler != 0) {
                return
            }
            haveFullSet = true
        }
        // a is the most recent pos, b is the second most-recent, c is the third
        val c = (cycler + 1) % 3
        val b = (cycler + 2) % 3
        val a = cycler
        // Unit vectors
        val xBA = (x[a] - x[b]) / mag(x[a] - x[b], y[a] - y[b])
        val yBA = (y[a] - y[b]) / mag(x[a] - x[b], y[a] - y[b])
        val xCB = (x[b] - x[c]) / mag(x[b] - x[c], y[b] - y[c])
        val yCB = (y[b] - y[c]) / mag(x[b] - x[c], y[b] - y[c])
        val zVec = xBA * yCB - yBA * xCB
        val theta = Math.toDegrees(Math.asin(zVec.toDouble())).toFloat()
        degs += theta
        val grads = degs.toInt() / GRAD_DEG
        if (grads != mCurrGrads) {
            if (grads > mCurrGrads) {
                mStrategy.onCcwGrad(v)
            } else {
                mStrategy.onCwGrad(v)
            }
            mCurrGrads = grads
        }
    }

    private fun mag(ax: Float, ay: Float): Float {
        return Math.sqrt(
            (Math.pow(ax.toDouble(), 2.0) + Math.pow(ay.toDouble(), 2.0)).toFloat()
                .toDouble()
        ).toFloat()
    }

    companion object {
        private const val GRAD_DEG = 90
        private const val POINT_DIST = 60.0f
    }
}