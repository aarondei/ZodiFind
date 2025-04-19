package cit.edu.zodifind

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cit.edu.zodifind.app.ZodiFindApplication
import cit.edu.zodifind.fragments.DatePickerFragment

class VerificationFirstActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification_first)

        val app = application as ZodiFindApplication

        val tvHello = findViewById<TextView>(R.id.tvHello)
        tvHello.text = "Hello, ${app.currentUser?.name ?: "User"}"

        // to import DatePicker
        if (savedInstanceState == null) { // loads the fragment only once
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, DatePickerFragment())
                .commit()
        }

        val btnProceed = findViewById<Button>(R.id.btnProceed)
        btnProceed.setOnClickListener(){
            startActivity(Intent(this, VerificationSecondActivity:: class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }
}