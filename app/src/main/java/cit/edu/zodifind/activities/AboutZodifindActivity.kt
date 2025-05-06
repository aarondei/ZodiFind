package cit.edu.zodifind.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import cit.edu.zodifind.R

class AboutZodifindActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_zodifind)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener(){
            val intent = Intent(this, SettingsActivity:: class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }
}