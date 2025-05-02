package cit.edu.zodifind

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class SplashAstrologyActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_astrology)


        val splashscreen = findViewById<ConstraintLayout>(R.id.splash_astrology)
        splashscreen.setOnClickListener(){
            val intent = Intent(this, AstrologyHomeActivity:: class.java) //TODO CHANGE
            startActivity(intent)

        }


        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)

        }

    }
}