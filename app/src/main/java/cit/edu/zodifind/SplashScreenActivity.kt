package cit.edu.zodifind

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.SurfaceHolder
import android.widget.VideoView
import androidx.constraintlayout.widget.ConstraintLayout

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : Activity() {

    private lateinit var videoView: VideoView
    private lateinit var mediaPlayer: MediaPlayer
    @SuppressLint("MissingInflatedId")
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)

        playVideoView()

        val constraintLayoutSplashScreen = findViewById<ConstraintLayout>(R.id.constraintLayoutSplashScreen)

        constraintLayoutSplashScreen.setOnClickListener(){
            val intent = Intent(this, LoginActivity:: class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun playVideoView() {
        videoView = findViewById(R.id.vvGalaxy)
        val videoUri = Uri.parse("android.resource://${packageName}/${R.raw.splashscreen_video}")
        videoView.setVideoURI(videoUri)
        videoView.setOnCompletionListener {
            videoView.start()
        }
        videoView.start()
    }

}

