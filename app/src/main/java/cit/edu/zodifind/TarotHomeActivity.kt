package cit.edu.zodifind

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TarotHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tarot_home)

        val items = listOf("Option 1", "Option 2", "Option 3")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, items)
        val dropdown = findViewById<AutoCompleteTextView>(R.id.dropdown)
        dropdown.setAdapter(adapter)

        dropdown.setOnClickListener {
            dropdown.showDropDown()
        }

        val btnReveal = findViewById<Button>(R.id.btnReveal)
        btnReveal.setOnClickListener {
            startActivity() // TODO INFLATE FRAGMENT DECK
        }


//        val items = listOf("Option 1", "Option 2", "Option 3")
//        val adapter = ArrayAdapter(this, R.layout.dropdown_item, items)
//        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.dropdownField)
//        autoCompleteTextView.setAdapter(adapter)

    }
}