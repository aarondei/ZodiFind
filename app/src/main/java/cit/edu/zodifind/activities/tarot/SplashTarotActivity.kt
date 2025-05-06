package cit.edu.zodifind.activities.tarot

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import cit.edu.zodifind.activities.BaseActivity
import cit.edu.zodifind.R

class SplashTarotActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_tarot)

        val splashscreen = findViewById<ConstraintLayout>(R.id.splash_tarot)
        splashscreen.setOnClickListener(){
            startActivity(Intent(this, TarotHomeActivity:: class.java))
        }


        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)

        }
    }
}