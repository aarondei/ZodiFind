package cit.edu.zodifind

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import cit.edu.zodifind.data.CapturedZodiacTempObject
import cit.edu.zodifind.fragments.RotatingStarFragment

class CalculatorResultActivity : BaseActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator_result)

        if (savedInstanceState == null) { // loads the fragment only once
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, RotatingStarFragment())
                .commit()
        }

        // set zodiac name
        val tvZodiacLabel = findViewById<TextView>(R.id.tvZodiacLabel)
        tvZodiacLabel.text = CapturedZodiacTempObject.capturedSign?.name
        correctTVLabel(tvZodiacLabel.text.toString())

        val btnAgain = findViewById<Button>(R.id.btnAgain)
        btnAgain.setOnClickListener {
            startActivity(Intent(this, CalculatorActivity::class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        val btnRead = findViewById<Button>(R.id.btnRead) // TODO UPDATE INTENT
        btnRead.setOnClickListener {
            startActivity(Intent(this, LibraryActivity::class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
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