package cit.edu.zodifind.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import cit.edu.zodifind.R
import cit.edu.zodifind.fragments.other.ItemsList
import cit.edu.zodifind.fragments.recycler_view.ItemsRV
import cit.edu.zodifind.wheel_of_fortune.WheelItem
import kotlinx.coroutines.launch


class ItemsFragment : Fragment(R.layout.fragment_items_list) {

    private var adapter: ItemsRV? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.list)
        val addBtn = view.findViewById<Button>(R.id.addBtn)
        adapter = ItemsRV(
            onEditClick = { pos ->
                val dialog = EditDialogFragment()
                dialog.arguments = bundleOf("pos" to pos)
                dialog.show(parentFragmentManager,"dddllglglsdfsdf")
            },
            onDeleteClick = ::onDelete
        )
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            ItemsList.flow.collect {
                adapter?.list = it
            }
        }
        addBtn.setOnClickListener {
            val dialog = EditDialogFragment()
            dialog.show(parentFragmentManager,"dddllglglsdfsdf")
        }
    }

    fun onDelete(item: WheelItem) {
        lifecycleScope.launch {
            ItemsList.flow.emit(ItemsList.flow.value.filter { it != item })
        }
    }
}