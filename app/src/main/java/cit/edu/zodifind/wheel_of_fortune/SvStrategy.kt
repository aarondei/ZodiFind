package cit.edu.zodifind.wheel_of_fortune

import android.view.View

// Sv == Scroll Value - Regarding the method of linear changing of a value by circular motion
/**
 * Provides the strategy for the scrollable value - i.e. handles the onScroll events
 */
internal interface SvStrategy {
    fun onStart(v: View?)
    fun onCwGrad(v: View?)
    fun onCcwGrad(v: View?)
    fun onFinish(v: View?)
}
