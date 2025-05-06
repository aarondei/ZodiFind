package cit.edu.zodifind.activities.palmistry

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import cit.edu.zodifind.activities.BaseActivity
import cit.edu.zodifind.R

class SplashPalmistryActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_palmistry)

        val splashscreen = findViewById<ConstraintLayout>(R.id.splash_palmistry)
        splashscreen.setOnClickListener(){
            val intent = Intent(this, PalmistryLibraryActivity:: class.java)
            startActivity(intent)
            finish()
        }


        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)

        }
    }
}