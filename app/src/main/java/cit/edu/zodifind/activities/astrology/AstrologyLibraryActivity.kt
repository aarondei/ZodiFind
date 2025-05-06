package cit.edu.zodifind.activities.astrology

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.commit
import cit.edu.zodifind.activities.BaseActivity
import cit.edu.zodifind.R
import cit.edu.zodifind.fragments.MenuFragment
import cit.edu.zodifind.fragments.ZodiacLibraryFragment

class AstrologyLibraryActivity : BaseActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var burgerMenuIcon: ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.astrology_library)

        // Initialize DrawerLayout and burger menu icon
        drawerLayout = findViewById(R.id.drawerLayout)
        burgerMenuIcon = findViewById(R.id.burgerMenuIcon)

        // Add the MenuDrawerFragment to the navigationDrawer FrameLayout
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

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ZodiacLibraryFragment())
            .commit()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}