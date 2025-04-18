package cit.edu.zodifind

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cit.edu.zodifind.app.ZodiFindApplication
import cit.edu.zodifind.fragments.RotatingStarFragment

class VerificationSecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification_second)

        val app = application as ZodiFindApplication

        // TODO REMOVE THIS AS IT CAN NOW ACCESS DATA
        Toast.makeText(this, "Birthdate: ${app.currentUser?.birthdate} and Sign: ${app.currentUser?.zodiacSign?.name}", Toast.LENGTH_LONG).show()


        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, RotatingStarFragment())
            .commit()

    }
}