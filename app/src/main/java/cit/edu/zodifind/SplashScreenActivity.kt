package cit.edu.zodifind

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)

        val btnGetStarted = findViewById<Button>(R.id.getstartedBtn)

        btnGetStarted.setOnClickListener(){
            val intent = Intent(this, LoginActivity:: class.java)
            startActivity(intent)
            finish()
        }
    }
}