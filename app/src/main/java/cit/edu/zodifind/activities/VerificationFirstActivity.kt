package cit.edu.zodifind.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import cit.edu.zodifind.R
import cit.edu.zodifind.app.ZodiFindApplication
import cit.edu.zodifind.fragments.DatePickerFragment

class VerificationFirstActivity : BaseActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.verification_first)

        val app = application as ZodiFindApplication

        val tvHello = findViewById<TextView>(R.id.tvHello)
        tvHello.text = "Hello, ${app.currentUser?.name ?: "User"},"

        val fragment = DatePickerFragment()
        fragment.arguments = Bundle().apply {
            putString("MODE", "USER")
        }


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit()
        }

        val btnProceed = findViewById<Button>(R.id.btnProceed)
        btnProceed.setOnClickListener(){

            startActivity(Intent(this, VerificationSecondActivity::class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }


    }
}