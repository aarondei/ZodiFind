package cit.edu.zodifind

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class SettingsActivity : Activity() {
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)

        val tvName = findViewById<TextView>(R.id.tvName)

        intent?.let {
            it.getStringExtra("username")?.let{username ->
                tvName.text = username
            }
        }

        val tvDeveloper = findViewById<TextView>(R.id.tvDeveloper)
        tvDeveloper.setOnClickListener(){
            val intent = Intent(this, DeveloperActivity:: class.java)
            startActivity(intent)
        }

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener(){
            val intent = Intent(this, ProfileActivity:: class.java)
            intent.putExtra("username", tvName.text.toString())
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }
}