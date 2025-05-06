package cit.edu.zodifind.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cit.edu.zodifind.R
import cit.edu.zodifind.adapters.ZodiacAdapter
import cit.edu.zodifind.data.astrology.ZodiacSign

class ZodiacLibraryFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var zodiacAdapter: ZodiacAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_zodiac_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerZodiac)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val zodiacList = ZodiacSign.getAllZodiacSigns()

        zodiacAdapter = ZodiacAdapter(zodiacList, requireContext())
        recyclerView.adapter = zodiacAdapter

        recyclerView.overScrollMode = RecyclerView.OVER_SCROLL_ALWAYS

        recyclerView.setPadding(
            recyclerView.paddingLeft,
            recyclerView.paddingTop,
            recyclerView.paddingRight,
            resources.getDimensionPixelSize(R.dimen.overscroll_bottom_padding)
        )
        recyclerView.clipToPadding = false
    }
}