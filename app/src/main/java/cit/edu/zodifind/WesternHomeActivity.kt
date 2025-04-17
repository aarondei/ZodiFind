package cit.edu.zodifind

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import cit.edu.zodifind.R

class WesternHomeActivity : Activity() {
    @Suppress("DEPRECATION")
    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.landingtab1)

        var user:String? = null

        intent?.let {
            it.getStringExtra("username")?.let{username ->
              user  = username;
            }
        }

        val btnProfile = findViewById<ImageView>(R.id.profilebtn)
        val btnCalendar = findViewById<ImageView>(R.id.btnCalendar)

        btnProfile.setOnClickListener(){
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("username", user)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

        }

        btnCalendar.setOnClickListener(){
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

        }
    }
}