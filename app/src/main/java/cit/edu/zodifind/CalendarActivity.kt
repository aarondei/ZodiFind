package cit.edu.zodifind

import android.os.Bundle
import android.widget.CalendarView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.commit
import cit.edu.zodifind.fragments.MenuFragment
import java.util.Calendar

class CalendarActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var burgerMenuIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar) // Use the layout with DrawerLayout

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

        val calendarView = findViewById<CalendarView>(R.id.calendarView)
        val zodiacText = findViewById<TextView>(R.id.tvZodiacSeason)

        //setting calendar to current date
        calendarView.date = System.currentTimeMillis()

        //updates what zodiac season based on current date
        val calendar = Calendar.getInstance()
        val todayMonth = calendar.get(Calendar.MONTH) + 1
        val todayDay = calendar.get(Calendar.DAY_OF_MONTH)
        zodiacText.text = getZodiacSign(todayMonth, todayDay)

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val season = getZodiacSign(month + 1, dayOfMonth) // month is 0-based
            zodiacText.text = season
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    //function to get season
    private fun getZodiacSign(month: Int, day: Int): String {
        return when {
            month == 3 && day >= 21 || month == 4 && day <= 19 -> "ARIES SEASON"
            month == 4 && day >= 20 || month == 5 && day <= 20 -> "TAURUS SEASON"
            month == 5 && day >= 21 || month == 6 && day <= 20 -> "GEMINI SEASON"
            month == 6 && day >= 21 || month == 7 && day <= 22 -> "CANCER SEASON"
            month == 7 && day >= 23 || month == 8 && day <= 22 -> "LEO SEASON"
            month == 8 && day >= 23 || month == 9 && day <= 22 -> "VIRGO SEASON"
            month == 9 && day >= 23 || month == 10 && day <= 22 -> "LIBRA SEASON"
            month == 10 && day >= 23 || month == 11 && day <= 21 -> "SCORPIO SEASON"
            month == 11 && day >= 22 || month == 12 && day <= 21 -> "SAGITTARIUS SEASON"
            month == 12 && day >= 22 || month == 1 && day <= 19 -> "CAPRICORN SEASON"
            month == 1 && day >= 20 || month == 2 && day <= 18 -> "AQUARIUS SEASON"
            month == 2 && day >= 19 || month == 3 && day <= 20 -> "PISCES SEASON"
            else -> "ZODIAC SEASON"
        }
    }
}