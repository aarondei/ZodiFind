package cit.edu.zodifind

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView

class DeveloperActivity : Activity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.developer)


        val btnBack = findViewById<ImageView>(R.id.back)
        btnBack.setOnClickListener(){
            val intent = Intent(this, SettingsActivity:: class.java)
            startActivity(intent)
        }
    }
}