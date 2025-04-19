package cit.edu.zodifind

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cit.edu.zodifind.app.ZodiFindApplication
import cit.edu.zodifind.fragments.RotatingStarFragment

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result)

        val app = application as ZodiFindApplication

        if (savedInstanceState == null) { // loads the fragment only once
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, RotatingStarFragment())
                .commit()
        }

        val tvZodiacLabel = findViewById<TextView>(R.id.tvZodiacLabel)
        tvZodiacLabel.text = app.currentUser?.zodiacSign?.name

        correctTVLabel(tvZodiacLabel.text.toString())

        val btnAgain = findViewById<Button>(R.id.btnAgain)
        btnAgain.setOnClickListener {
            startActivity(Intent(this, CalculatorActivity::class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        val btnRead = findViewById<Button>(R.id.btnRead)
        btnRead.setOnClickListener {
            startActivity(Intent(this, LibraryActivity::class.java))
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