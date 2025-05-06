package cit.edu.zodifind

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cit.edu.zodifind.app.ZodiFindApplication
import cit.edu.zodifind.fragments.RotatingStarFragment

class VerificationSecondActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.verification_second)

        val app = application as ZodiFindApplication

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, RotatingStarFragment())
                .commit()
        }

        // set zodiac name
        val tvZodiacLabel = findViewById<TextView>(R.id.tvZodiacLabel)
        tvZodiacLabel.text = app.currentUser?.zodiacSign?.name
        correctTVLabel(tvZodiacLabel.text.toString())

        val btnStart = findViewById<Button>(R.id.btnStart)
        btnStart.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun correctTVLabel(word: String): Unit {

        if (word.isNotEmpty()) {

            val first = word[0].lowercaseChar()

            if (first in listOf('a', 'e', 'i', 'o', 'u')) {
                val tvLabel = findViewById<TextView>(R.id.tvLabel)
                tvLabel.text = "${tvLabel.text}N"
            }
        }
    }
}