package cit.edu.zodifind

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashPalmistryActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_palmistry)

        val splashscreen = findViewById<ConstraintLayout>(R.id.splash_palmistry)
        splashscreen.setOnClickListener(){
            val intent = Intent(this, PalmistryLibraryActivity:: class.java) //TODO CHANGE
            startActivity(intent)
            finish()
        }
    }
}