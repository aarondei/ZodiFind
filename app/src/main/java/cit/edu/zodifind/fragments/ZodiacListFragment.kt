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
import cit.edu.zodifind.data.ZodiacItem

class ZodiacListFragment : Fragment() {

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

        val zodiacList = listOf(
            ZodiacItem("ARIES", "MAR 21 - APR 19", R.drawable.aries_icon),
            ZodiacItem("TAURUS", "APR 20 - MAY 20", R.drawable.taurus_icon),
            ZodiacItem("GEMINI", "MAY 21 - JUN 20", R.drawable.gemini_icon),
            ZodiacItem("CANCER", "JUN 21 - JUL 22", R.drawable.cancer_icon),
            ZodiacItem("LEO", "JUL 23 - AUG 22", R.drawable.leo_icon),
            ZodiacItem("VIRGO", "AUG 23 - SEP 22", R.drawable.virgo_icon),
            ZodiacItem("LIBRA", "SEP 23 - OCT 22", R.drawable.libra_icon),
            ZodiacItem("SCORPIO", "OCT 23 - NOV 21", R.drawable.scorpio_icon),
            ZodiacItem("SAGITTARIUS", "NOV 22 - DEC 21", R.drawable.sagittarius_icon),
            ZodiacItem("CAPRICORN", "DEC 22 - JAN 19", R.drawable.capricorn_icon),
            ZodiacItem("AQUARIUS", "JAN 20 - FEB 18", R.drawable.aquarius_icon),
            ZodiacItem("PISCES", "FEB 19 - MAR 20", R.drawable.pisces_icon)
        )

        zodiacAdapter = ZodiacAdapter(zodiacList, requireContext()) // Pass requireContext()
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