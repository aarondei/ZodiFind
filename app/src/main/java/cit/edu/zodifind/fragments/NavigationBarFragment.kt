package cit.edu.zodifind.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cit.edu.zodifind.activities.CalculatorActivity
import cit.edu.zodifind.activities.CalendarActivity
import cit.edu.zodifind.activities.HomeActivity
import cit.edu.zodifind.activities.astrology.AstrologyLibraryActivity
import cit.edu.zodifind.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavigationBarFragment : Fragment() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_nav, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNavigationView = view.findViewById(R.id.bottomNav)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(requireContext(), HomeActivity::class.java)
                    startActivity(intent)
                    activity?.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    true
                }
                R.id.nav_library -> {
                    val intent = Intent(requireContext(), AstrologyLibraryActivity::class.java)
                    startActivity(intent)
                    activity?.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    true
                }
                R.id.nav_finder -> {
                    val intent = Intent(requireContext(), CalculatorActivity::class.java)
                    startActivity(intent)
                    activity?.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    true
                }
                R.id.nav_calendar -> {
                    val intent = Intent(requireContext(), CalendarActivity::class.java)
                    startActivity(intent)
                    activity?.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    true
                }
                else -> false
            }
        }
    }
}