package cit.edu.zodifind.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import cit.edu.zodifind.CalculatorActivity
import cit.edu.zodifind.CalendarActivity
import cit.edu.zodifind.HomeActivity
import cit.edu.zodifind.LibraryActivity
import cit.edu.zodifind.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class LibraryFocusedItemBirthdateFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library_focuseditem_birthdate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}