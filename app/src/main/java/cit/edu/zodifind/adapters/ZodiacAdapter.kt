package cit.edu.zodifind.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cit.edu.zodifind.R
import cit.edu.zodifind.data.ZodiacItem

class ZodiacAdapter(private val zodiacList: List<ZodiacItem>) :
    RecyclerView.Adapter<ZodiacAdapter.ZodiacViewHolder>() {

    inner class ZodiacViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageZodiac: ImageView = itemView.findViewById(R.id.imageZodiac)
        val tvZodiacName: TextView = itemView.findViewById(R.id.tvZodiacName)
        val tvZodiacDate: TextView = itemView.findViewById(R.id.tvZodiacDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZodiacViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_zodiac, parent, false)
        return ZodiacViewHolder(view)
    }

    override fun onBindViewHolder(holder: ZodiacViewHolder, position: Int) {
        val item = zodiacList[position]
        holder.imageZodiac.setImageResource(item.imageResId)
        holder.tvZodiacName.text = item.name
        holder.tvZodiacDate.text = item.dateRange
    }

    override fun getItemCount(): Int = zodiacList.size
}