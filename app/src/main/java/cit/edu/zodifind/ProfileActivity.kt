package cit.edu.zodifind

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import cit.edu.zodifind.R

class ProfileActivity : Activity() {
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        val txtName = findViewById<TextView>(R.id.name)

        intent?.let {
            it.getStringExtra("username")?.let{username ->
                txtName.setText(username)
            }
        }

        val toSettings = findViewById<ImageView>(R.id.settings)
        toSettings.setOnClickListener(){
            val intent = Intent(this, SettingsActivity:: class.java)
            intent.putExtra("username", txtName.text.toString())
            startActivity(intent)
        }

        val toHome = findViewById<ImageView>(R.id.wheelbtn)
        toHome.setOnClickListener(){
            val intent = Intent(this, WesternHomeActivity:: class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        val btnBack = findViewById<ImageView>(R.id.back)
        btnBack.setOnClickListener(){
            val intent = Intent(this, WesternHomeActivity:: class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

        }
    }


}