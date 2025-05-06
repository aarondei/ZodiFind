package cit.edu.zodifind.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import cit.edu.zodifind.R

class DeveloperActivity : BaseActivity() {
    @Suppress("DEPRECATION")
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.developers)


        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        }
    }
}