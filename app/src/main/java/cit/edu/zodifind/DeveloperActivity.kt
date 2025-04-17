package cit.edu.zodifind

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import cit.edu.zodifind.R

class DeveloperActivity : Activity() {
    @Suppress("DEPRECATION")
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.developers)


        val btnBack = findViewById<ImageView>(R.id.back)
        btnBack.setOnClickListener(){
            val intent = Intent(this, SettingsActivity:: class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }
}