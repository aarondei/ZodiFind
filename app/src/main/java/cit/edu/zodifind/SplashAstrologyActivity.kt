package cit.edu.zodifind

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class SplashAstrologyActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_astrology)


        val splashscreen = findViewById<ConstraintLayout>(R.id.splash_astrology)
        splashscreen.setOnClickListener(){
            val intent = Intent(this, SplashTarotActivity:: class.java) //TODO CHANGE
            startActivity(intent)

        }

    }
}