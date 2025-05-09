package cit.edu.zodifind.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.constraintlayout.widget.ConstraintLayout
import cit.edu.zodifind.R

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : BaseActivity() {

    private lateinit var videoView: VideoView
    @SuppressLint("MissingInflatedId")
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)

        playVideoView()


        val constraintLayoutSplashScreen = findViewById<ConstraintLayout>(R.id.constraintLayoutSplashScreen)
        constraintLayoutSplashScreen.setOnClickListener(){
            val intent = Intent(this, LoginActivity:: class.java) // TODO REVERT BACK AFTER TEST
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

