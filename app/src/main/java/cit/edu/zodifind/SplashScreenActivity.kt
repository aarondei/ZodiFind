package cit.edu.zodifind

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.RelativeLayout

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : Activity() {

    private lateinit var surfaceView: SurfaceView
    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)

        surfaceView = findViewById(R.id.svGalaxy)
        playBackgroundVideo()

        val rlSplashScreenLayout = findViewById<RelativeLayout>(R.id.rlSplashScreenLayout)

        rlSplashScreenLayout.setOnClickListener(){
            val intent = Intent(this, LoginActivity:: class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun playBackgroundVideo() {
        val holder: SurfaceHolder = surfaceView.holder

        mediaPlayer = MediaPlayer()

        // Set up the SurfaceHolder callback
        holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                // Set up MediaPlayer when surface is created
                val videoPath = "android.resource://${packageName}/${R.raw.galaxy}"
                val uri: Uri = Uri.parse(videoPath)

                mediaPlayer.setDataSource(this@SplashScreenActivity, uri)
                mediaPlayer.setDisplay(holder)  // Set the display to SurfaceView
                mediaPlayer.isLooping = true  // Enable looping

                mediaPlayer.prepareAsync()

                mediaPlayer.setOnPreparedListener {
                    mediaPlayer.start()  // Start the video
                }
            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
                // Optional: handle surface size changes if needed
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                mediaPlayer.release()  // Release the MediaPlayer when the surface is destroyed
            }
        })
    }


}

