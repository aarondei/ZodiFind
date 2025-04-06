package cit.edu.zodifind.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import cit.edu.zodifind.R
import kotlinx.coroutines.launch
import cit.edu.zodifind.fragments.other.ItemsList
import cit.edu.zodifind.wheel_of_fortune.WheelOfFortuneView

class WheelFragment : Fragment(R.layout.fragment_wheel) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val wheelOfFortune = view.findViewById<WheelOfFortuneView>(R.id.wheel)
        val spinBtn = view.findViewById<Button>(R.id.spin_btn)
        val itemsBtn = view.findViewById<Button>(R.id.items_btn)
        itemsBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .addToBackStack("whl")
                .add(R.id.main_container, ItemsFragment::class.java, null)
                .hide(this)
                .commit()
        }
        lifecycleScope.launch {
            ItemsList.flow.collect {
                wheelOfFortune.items = it
            }
        }
        spinBtn.setOnClickListener {
            wheelOfFortune.animTest()
        }
    }
}