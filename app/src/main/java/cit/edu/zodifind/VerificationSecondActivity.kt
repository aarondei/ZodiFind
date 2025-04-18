package cit.edu.zodifind

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cit.edu.zodifind.fragments.RotatingStarFragment

class VerificationSecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification_second)


        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, RotatingStarFragment())
            .commit()

    }
}