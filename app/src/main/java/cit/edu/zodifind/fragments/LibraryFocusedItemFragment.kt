package cit.edu.zodifind.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentContainerView
import cit.edu.zodifind.R
import cit.edu.zodifind.helpers.ZodiacSign

class LibraryFocusedItemFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_library_focuseditem, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentContainer = view.findViewById<FragmentContainerView>(R.id.fragmentContainer)
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



//        val bundle = Bundle()
//        bundle.putString("key", key)
//        bundle.putString("MODE", "library")
//
//        val secondFragment = RotatingStarFragment()
//        secondFragment.arguments = bundle
//
//        parentFragmentManager.beginTransaction()
//            .replace(R.id.fragment_container, secondFragment)
//            .addToBackStack(null)
//            .commit()

//        val result = Bundle().apply {
//            putString("key", key)
//        }
//        parentFragmentManager.setFragmentResult("passDataKey", result)
    }
}