package cit.edu.zodifind.activities.astrology

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import kotlin.math.atan2
import kotlin.math.hypot
import android.os.Handler
import android.os.Looper
import android.widget.OverScroller
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import cit.edu.zodifind.activities.BaseActivity
import cit.edu.zodifind.R

class AstrologyHomeActivity : BaseActivity() {

    private var gestureDetector: GestureDetector? = null
    private var rotationAngle = 0f

    private lateinit var scroller: OverScroller
    private var handler = Handler(Looper.getMainLooper())
    private var isSpinning = false

    private val totalSlices = 12
    private val sliceAngle = 360f / totalSlices


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.astrology_home)

        val zodiacWheel = findViewById<ImageView>(R.id.zodiacWheel)

        // Drag rotate
        zodiacWheel.setOnTouchListener(object : View.OnTouchListener {
            private var prevAngle = 0f

            override fun onTouch(v: View, event: MotionEvent): Boolean {
                // Handle fling
                gestureDetector?.onTouchEvent(event)

                // Handle drag
                val centerX = v.width / 2f
                val centerY = v.height / 2f
                val x = event.x - centerX
                val y = event.y - centerY
                val angle = Math.toDegrees(atan2(y.toDouble(), x.toDouble())).toFloat()

                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        prevAngle = angle
                        return true
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val delta = angle - prevAngle
                        v.rotation += delta
                        rotationAngle = v.rotation % 360
                        prevAngle = angle
                        return true
                    }
                }
                return false
            }
        })


        scroller = OverScroller(this, DecelerateInterpolator())
        setupSmartSpin(zodiacWheel)

        val burgerMenuIcon = findViewById<ImageView>(R.id.burgerMenuIcon)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        burgerMenuIcon.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupSmartSpin(imageView: ImageView) {
        gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {

                scroller.forceFinished(true)

                val velocity = hypot(velocityX, velocityY).toInt()
                val direction = if (velocityX * velocityY < 0) 1 else -1
                val finalDegrees = direction * velocity

                scroller.fling(
                    0, 0, 0, finalDegrees,
                    0, 0, Int.MIN_VALUE, Int.MAX_VALUE
                )

                isSpinning = true
                spinRunnable(imageView)
                return true
            }
        })

        imageView.setOnTouchListener(object : View.OnTouchListener {
            private var prevAngle = 0f

            override fun onTouch(v: View, event: MotionEvent): Boolean {
                gestureDetector!!.onTouchEvent(event)

                val centerX = v.width / 2f
                val centerY = v.height / 2f
                val x = event.x - centerX
                val y = event.y - centerY
                val angle = Math.toDegrees(atan2(y.toDouble(), x.toDouble())).toFloat()

                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        scroller.forceFinished(true)
                        isSpinning = false
                        prevAngle = angle
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val delta = angle - prevAngle
                        v.rotation += delta
                        rotationAngle = v.rotation % 360
                        prevAngle = angle
                    }
                }
                return true
            }
        })
    }

    private fun spinRunnable(view: ImageView) {
        val mediaPlayer = MediaPlayer.create(this, R.raw.roulette)
        mediaPlayer.start()

        handler.post(object : Runnable {
            override fun run() {
                if (scroller.computeScrollOffset()) {
                    val delta = scroller.currY - rotationAngle
                    view.rotation += delta
                    rotationAngle = view.rotation % 360
                    handler.post(this)
                } else {
                    isSpinning = false
                    snapToNearestSlice(view)
                }
            }
        })
    }
    private fun snapToNearestSlice(view: ImageView) {
        val currentRotation = view.rotation % 360
        val snappedAngle = (Math.round(currentRotation / sliceAngle) * sliceAngle) % 360

        ValueAnimator.ofFloat(currentRotation, snappedAngle).apply {
            duration = 300
            interpolator = DecelerateInterpolator()
            addUpdateListener {
                view.rotation = it.animatedValue as Float
            }
            start()
        }

        rotationAngle = snappedAngle

        startActivity(Intent(this, AstrologyLibraryActivity::class.java))
    }

}
