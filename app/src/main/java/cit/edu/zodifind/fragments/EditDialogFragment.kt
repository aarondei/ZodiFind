package cit.edu.zodifind.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.view.Gravity
import android.view.View
import android.view.ViewGroup.LayoutParams
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupWindow
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import cit.edu.zodifind.R
import kotlinx.coroutines.launch
import cit.edu.zodifind.fragments.other.ItemsList
import cit.edu.zodifind.fragments.recycler_view.IconsRVA
import cit.edu.zodifind.wheel_of_fortune.WheelItem


class EditDialogFragment : DialogFragment(R.layout.layout_item_creation) {

    private var iconRes: Int? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pos = arguments?.getInt("pos")
        val addBtn = view.findViewById<Button>(R.id.add)
        val randomColorBtn = view.findViewById<Button>(R.id.random)
        val nameText = view.findViewById<EditText>(R.id.name)
        val valueText = view.findViewById<EditText>(R.id.value)
        val iconBtn = view.findViewById<ImageView>(R.id.item_icon)
        val red = view.findViewById<EditText>(R.id.red)
        val green = view.findViewById<EditText>(R.id.green)
        val blue = view.findViewById<EditText>(R.id.blue)

        pos?.let { position ->
            val item = ItemsList.flow.value[position]
            nameText.setText(item.text)
            valueText.setText(item.weight.toString())
            red.setText(Color.red(item.color).toString())
            green.setText(Color.green(item.color).toString())
            blue.setText(Color.blue(item.color).toString())
            iconRes = item.icon
            iconBtn.setImageResource(item.icon ?: R.drawable.baseline_close_24)
            randomColorBtn.setBackgroundColor(item.color)
        }
        iconBtn.setOnClickListener {
            val popupView = layoutInflater.inflate(R.layout.popup_list, null)
            val iconsRcView = popupView.findViewById<RecyclerView>(R.id.popup_list)
            val popupWindow =
                PopupWindow(popupView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true)
            val adapter = IconsRVA(
                list = listOf(
                    null,
                    R.drawable.android,
                    R.drawable.apple,
                    R.drawable.youtube_color_svgrepo_com,
                    R.drawable.flag_ru_svgrepo_com,
                    R.drawable.flag_for_flag_belarus_svgrepo_com,
                    R.drawable.flag_kz_svgrepo_com,
                    R.drawable.flag_ua_svgrepo_com,
                    R.drawable.samsung_logo_svgrepo_com,
                    R.drawable.xiaomi_logo_svgrepo_com,
                    R.drawable.clown_face_svgrepo_com,
                ),
                onClick = { res ->
                    res?.let { iconBtn.setImageResource(it) }
                    iconRes = res
                    popupWindow.dismiss()
                }
            )
            iconsRcView.adapter = adapter
            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)
            popupView.setOnTouchListener { v, event ->
                popupWindow.dismiss()
                true
            }
        }
        randomColorBtn.setOnClickListener {
            val r = (0..255).random()
            val g = (0..255).random()
            val b = (0..255).random()
            randomColorBtn.setBackgroundColor(Color.rgb(r, g, b))
            red.text = Editable.Factory.getInstance().newEditable(r.toString())
            green.text = Editable.Factory.getInstance().newEditable(g.toString())
            blue.text = Editable.Factory.getInstance().newEditable(b.toString())
        }
        addBtn.setOnClickListener {
            val items = ItemsList.flow.value.toMutableList()
            if (pos != null) {
                val item = ItemsList.flow.value[pos]
                items.removeAt(pos)
                items.add(
                    pos, item.copy(
                        text = nameText.text.toString(),
                        weight = valueText.text.toString().toInt(),
                        color = Color.rgb(
                            red.text.toString().toInt(),
                            green.text.toString().toInt(),
                            blue.text.toString().toInt()
                        ),
                        icon = iconRes
                    )
                )
            } else {
                items.add(
                    WheelItem(
                        text = nameText.text.toString(),
                        weight = valueText.text.toString().toInt(),
                        color = Color.rgb(
                            red.text.toString().toInt(),
                            green.text.toString().toInt(),
                            blue.text.toString().toInt()
                        ),
                        icon = iconRes
                    )
                )
            }

            lifecycleScope.launch {
                ItemsList.flow.emit(items)
            }
        }
    }
}