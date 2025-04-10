package cit.edu.zodifind

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import cit.edu.zodifind.R

class WesternHomeActivity : Activity() {
    @SuppressLint("WrongViewCast")
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

        btnProfile.setOnClickListener(){
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("username", user)
            startActivity(intent)

        }
    }
}