package cit.edu.zodifind.fragments

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import cit.edu.zodifind.R
import cit.edu.zodifind.app.ZodiFindApplication

class RotatingStarFragment : Fragment(R.layout.fragment_rotatingstar) {

    private lateinit var innerCircle: ImageView
    private lateinit var ellipseLeft: ImageView
    private lateinit var ellipseRight: ImageView
    private lateinit var star: ImageView
    private lateinit var invertedStar: ImageView
    private lateinit var zodiacContainer: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_rotatingstar, container, false)

        val app = requireActivity().application as ZodiFindApplication

        innerCircle = view.findViewById(R.id.innerCircle)
        ellipseLeft = view.findViewById(R.id.ellipseLeft)
        ellipseRight = view.findViewById(R.id.ellipseRight)
        star = view.findViewById(R.id.star)
        invertedStar = view.findViewById(R.id.invertedStar)
        zodiacContainer = view.findViewById(R.id.zodiacContainer)

        app.currentUser?.zodiacSign?.icon?.let { zodiacContainer.setImageResource(it) }

        startRotation(innerCircle, 10000f)
        startRotation(ellipseLeft, 12000f)
        startCounterRotation(ellipseRight, 12000f)
        startRotation(star, 15000f)
        startCounterRotation(invertedStar, 15000f)

        return view
    }
    private fun startRotation(view: ImageView, duration: Float) {
        val animator = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f)
        animator.duration = duration.toLong()
        animator.repeatCount = ValueAnimator.INFINITE
        animator.interpolator = LinearInterpolator()
        animator.start()
    }
    private fun startCounterRotation(view: ImageView, duration: Float) {
        val animator = ObjectAnimator.ofFloat(view, "rotation", 0f, -360f)
        animator.duration = duration.toLong()
        animator.repeatCount = ValueAnimator.INFINITE
        animator.interpolator = LinearInterpolator()
        animator.start()
    }
}