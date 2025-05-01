package cit.edu.zodifind

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashTarotActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_tarot)

        val splashscreen = findViewById<ConstraintLayout>(R.id.splash_tarot)
        splashscreen.setOnClickListener(){
            startActivity(Intent(this, TarotHomeActivity:: class.java))
        }
    }
}