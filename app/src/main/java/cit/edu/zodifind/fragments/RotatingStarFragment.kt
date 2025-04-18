package cit.edu.zodifind.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cit.edu.zodifind.R

class RotatingStarFragment : Fragment(R.layout.fragment_rotatingstar) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val rotatingView = view.findViewById<RotatingStarView>(R.id.rotatingView)
        rotatingView.startRotation()
    }
}