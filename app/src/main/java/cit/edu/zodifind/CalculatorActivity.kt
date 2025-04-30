package cit.edu.zodifind

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.commit
import cit.edu.zodifind.app.ZodiFindApplication
import cit.edu.zodifind.fragments.DatePickerFragment
import cit.edu.zodifind.fragments.MenuFragment

class CalculatorActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var burgerMenuIcon: ImageView

    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator)

        val app = application as ZodiFindApplication

        drawerLayout = findViewById(R.id.drawerLayout)
        burgerMenuIcon = findViewById(R.id.burgerMenuIcon)


        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add(R.id.navigationDrawer, MenuFragment.newInstance())
            }
        }

        burgerMenuIcon.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        val tvHello = findViewById<TextView>(R.id.tvHello)
        tvHello.text = "${app.currentUser?.name ?: "User"},"

        val fragment = DatePickerFragment()
        fragment.arguments = Bundle().apply {
            putString("MODE", "OBJECT")
        }

        if (savedInstanceState == null) { // loads the fragment only once
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit()
        }

        val btnProceed = findViewById<Button>(R.id.btnProceed)
        btnProceed.setOnClickListener {
            startActivity(Intent(this, CalculatorResultActivity::class.java))
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}