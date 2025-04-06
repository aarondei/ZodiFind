package cit.edu.zodifind.fragments.recycler_view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import cit.edu.zodifind.R
import cit.edu.zodifind.wheel_of_fortune.WheelItem

@SuppressLint("NotifyDataSetChanged")
class ItemsRV(
    val onEditClick: (Int) -> Unit,
    val onDeleteClick: (WheelItem) -> Unit
) : RecyclerView.Adapter<ItemsRV.ItemVH>() {

    var list = listOf<WheelItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item, parent, false)
        return ItemVH(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ItemVH, position: Int) {
        holder.bind(position)
    }

    inner class ItemVH(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(position: Int) {
            val name = view.findViewById<TextView>(R.id.item_name)
            val value = view.findViewById<TextView>(R.id.item_value)
            val color = view.findViewById<View>(R.id.item_color)
            val icon = view.findViewById<ImageView>(R.id.item_icon)
            val editBtn = view.findViewById<Button>(R.id.edit_btn)
            val deleteBtn = view.findViewById<Button>(R.id.delete_btn)
            val item = list[position]

            name.text = item.text
            value.text = item.weight.toString()
            color.setBackgroundColor(item.color)
            color.foreground = ResourcesCompat.getDrawable(
                view.context.resources,
                R.drawable.border,
                view.context.theme
            )

            if (item.icon != null)
                icon.setImageResource(item.icon)
            else icon.setImageResource(R.drawable.baseline_close_24)

            editBtn.setOnClickListener {
                onEditClick(position)
            }
            deleteBtn.setOnClickListener {
                onDeleteClick(item)
            }
        }
    }
}

