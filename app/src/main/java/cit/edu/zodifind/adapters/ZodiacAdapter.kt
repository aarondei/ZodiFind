package cit.edu.zodifind.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cit.edu.zodifind.R

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import cit.edu.zodifind.data.astrology.ZodiacSign

import cit.edu.zodifind.fragments.ViewPagerFragment



class ZodiacAdapter(private val zodiacList: List<ZodiacSign>, private val context: Context) :
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

                val activity = view.context as AppCompatActivity
                val fragment = ViewPagerFragment().apply {
                    arguments = Bundle().apply {
                        putString("zodiac_sign", selectedZodiac.name)
                    }
                }

                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.drawerLayout, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
//            val position = adapterPosition
//            if (position != RecyclerView.NO_POSITION && view != null) {
//                val selectedZodiac = zodiacList[position] // TODO CLEAN
//                val intent = Intent(context, LibraryFocusedItemActivity::class.java)
//                intent.putExtra("zodiac", selectedZodiac.name)
//
//                CapturedZodiacTempObject.capturedSign = selectedZodiac
//
//                context.startActivity(intent)
//            }
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZodiacViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_library_item_zodiac, parent, false)
        return ZodiacViewHolder(view)
    }

    override fun onBindViewHolder(holder: ZodiacViewHolder, position: Int) {
        val item = zodiacList[position]
        holder.imageZodiac.setImageResource(item.faceIcon)
        holder.tvZodiacName.text = item.name
        holder.tvZodiacDate.text = item.concatStartEndDatesCapsCase()
    }

    override fun getItemCount(): Int = zodiacList.size
}