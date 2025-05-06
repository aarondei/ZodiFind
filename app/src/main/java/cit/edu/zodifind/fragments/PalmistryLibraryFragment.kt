package cit.edu.zodifind.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cit.edu.zodifind.R
import cit.edu.zodifind.adapters.PalmistryAdapter
import cit.edu.zodifind.data.palmistry.PalmLines

class PalmistryLibraryFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var palmistryAdapter: PalmistryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_palmistry_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerPalmistry)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val palmLinesList = PalmLines.getAllPalmLines()

        palmistryAdapter = PalmistryAdapter(palmLinesList, requireContext())
        recyclerView.adapter = palmistryAdapter

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