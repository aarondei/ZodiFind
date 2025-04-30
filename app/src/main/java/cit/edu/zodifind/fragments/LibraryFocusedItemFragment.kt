package cit.edu.zodifind.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import cit.edu.zodifind.R
import cit.edu.zodifind.data.astrology.ZodiacSign

class LibraryFocusedItemFragment : Fragment() {

    //private lateinit var TabLayoutView: Linear
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_library_focuseditem, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TabLayoutView = view.findViewById(R.id.tab_layout)

        val tvZodiacFocusedItemName = view.findViewById<TextView>(R.id.tvZodiacFocusedItemName)
        val tvZodiacFocusedItemTitle = view.findViewById<TextView>(R.id.tvZodiacFocusedItemTitle)

        val key = arguments?.getString("key")
        val zodiac = key?.let { ZodiacSign.valueOf(it) }

        if (zodiac != null) {
            tvZodiacFocusedItemName.text = zodiac.name
        }
        if (zodiac != null) {
            tvZodiacFocusedItemTitle.text = zodiac.representation
        }
    }
}