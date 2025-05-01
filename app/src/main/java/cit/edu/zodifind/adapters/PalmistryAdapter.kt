package cit.edu.zodifind.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import cit.edu.zodifind.R
import cit.edu.zodifind.fragments.PalmLineDetailFragment
import cit.edu.zodifind.fragments.ViewPagerFragment
import cit.edu.zodifind.luck.PalmModel

class PalmistryAdapter(private val palmList: List<PalmModel>, private val context: Context) :
    RecyclerView.Adapter<PalmistryAdapter.PalmViewHolder>() {

    inner class PalmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val imagePalm: ImageView = itemView.findViewById(R.id.imgPalm) // Correct ID
        val tvPalmName: TextView = itemView.findViewById(R.id.tvPalmName) // Correct ID

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION && view != null) {
                val selectedPalm = palmList[position]

                val activity = view.context as AppCompatActivity
                val fragment = PalmLineDetailFragment().apply {
                    arguments = Bundle().apply {
                        putInt("image_res_id", selectedPalm.image)
                        putInt("title_res_id", selectedPalm.title)
                        putInt("domain_res_id", selectedPalm.domain)
                        putInt("content_res_id", selectedPalm.content)
                    }
                }

                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.drawerLayout, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PalmViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_library_item_palmistry, parent, false) // Inflate the correct layout
        return PalmViewHolder(view)
    }

    override fun onBindViewHolder(holder: PalmViewHolder, position: Int) {
        val item = palmList[position]
        holder.imagePalm.setImageResource(item.image)
        holder.tvPalmName.text = context.getString(item.title)
    }

    override fun getItemCount(): Int = palmList.size
}