package cit.edu.zodifind

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import cit.edu.zodifind.R

class SettingsActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)

        val toDeveloper = findViewById<TextView>(R.id.txtDevelopers)
        toDeveloper.setOnClickListener(){
            val intent = Intent(this, DeveloperActivity:: class.java)
            startActivity(intent)
        }

        val btnBack = findViewById<ImageView>(R.id.back)
        btnBack.setOnClickListener(){
            val intent = Intent(this, ProfileActivity:: class.java)
            startActivity(intent)
        }
    }
}