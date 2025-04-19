package cit.edu.zodifind.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cit.edu.zodifind.R
import cit.edu.zodifind.data.ZodiacItem

import android.content.Context
import android.content.Intent
import cit.edu.zodifind.LibBirthdateActivity

class ZodiacAdapter(private val zodiacList: List<ZodiacItem>, private val context: Context) :
    RecyclerView.Adapter<ZodiacAdapter.ZodiacViewHolder>() {

    inner class ZodiacViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val imageZodiac: ImageView = itemView.findViewById(R.id.imageZodiac)
        val tvZodiacName: TextView = itemView.findViewById(R.id.tvZodiacName)
        val tvZodiacDate: TextView = itemView.findViewById(R.id.tvZodiacDate)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION && view != null) {
                val selectedZodiac = zodiacList[position]
                val intent = Intent(context, LibBirthdateActivity::class.java) // Replace ZodiacDetailActivity
                intent.putExtra("zodiac_name", selectedZodiac.name)
                intent.putExtra("zodiac_date", selectedZodiac.dateRange)
                intent.putExtra("zodiac_image", selectedZodiac.imageResId)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZodiacViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_library_item_zodiac, parent, false)
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