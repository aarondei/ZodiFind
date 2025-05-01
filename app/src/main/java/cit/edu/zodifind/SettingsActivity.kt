package cit.edu.zodifind

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class SettingsActivity : BaseActivity() {
    @SuppressLint("MissingInflatedId")
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
        btnBack.setOnClickListener {

            val intent = Intent(this, HomeActivity:: class.java)
            startActivity(intent)
//            val resultIntent = Intent()
//            resultIntent.putExtra("username", tvName.text.toString())
//            setResult(RESULT_OK, resultIntent)
//            finish()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        val btnAbout = findViewById<ImageView>(R.id.btnToAbout)
        btnAbout.setOnClickListener(){
            val intent = Intent(this, AboutZodifindActivity:: class.java)
            startActivity(intent)
        }

        val btnDev = findViewById<ImageView>(R.id.btnToDev)
        btnDev.setOnClickListener(){
            val intent = Intent(this, DeveloperActivity:: class.java)
            startActivity(intent)
        }

        val btnFAQ = findViewById<ImageView>(R.id.btnToFaq)
        btnFAQ.setOnClickListener(){
            val intent = Intent(this, FaqActivity:: class.java)
            startActivity(intent)
        }


    }
}