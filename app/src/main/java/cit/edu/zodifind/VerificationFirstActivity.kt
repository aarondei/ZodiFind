package cit.edu.zodifind

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cit.edu.zodifind.fragments.DatePicker

class VerificationFirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification_first)

        // TODO user's name
        val tvHello = findViewById<TextView>(R.id.tvHello)

        // to import DatePicker
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, DatePicker())
                .commit()
        }

        val btnProceed = findViewById<Button>(R.id.btnProceed)
        btnProceed.setOnClickListener(){
            startActivity(Intent(this, VerificationFirstActivity:: class.java))
        }
    }
}