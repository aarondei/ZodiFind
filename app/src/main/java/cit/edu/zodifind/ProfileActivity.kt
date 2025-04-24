package cit.edu.zodifind

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : Activity() {
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

        //nav bar intents i dont think need pani
//        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
//
//        bottomNav.setOnItemSelectedListener {
//            when (it.itemId) {
//                R.id.nav_home -> startActivity(Intent(this, HomeActivity::class.java))
//                R.id.nav_library -> startActivity(Intent(this, LibraryActivity::class.java))
//                R.id.nav_finder -> startActivity(Intent(this, CalculatorActivity::class.java))
//                R.id.nav_calendar -> startActivity(Intent(this, CalendarActivity::class.java))
//            }
//            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
//            true
//        }
    }


}