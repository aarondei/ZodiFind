package cit.edu.zodifind

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity() {
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        val tvName = findViewById<TextView>(R.id.tvName)

        intent?.let {
            it.getStringExtra("username")?.let{username ->
                tvName.setText(username)
            }
        }

    }


}