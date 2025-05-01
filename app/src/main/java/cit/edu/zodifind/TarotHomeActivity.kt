package cit.edu.zodifind

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TarotHomeActivity : AppCompatActivity() {

    private var selectedItem: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tarot_home)

        val items = listOf("Love", "Career", "Money", "Personal Growth", "Health", "Life Purpose", "Spiritual Guidance", "Decision", "Family", "Friendship")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, items)
        val dropdown = findViewById<AutoCompleteTextView>(R.id.dropdown)
        dropdown.setAdapter(adapter)

        dropdown.setOnClickListener {
            dropdown.showDropDown()
        }
        dropdown.dropDownHeight = 1100


        dropdown.setOnItemClickListener { parent, view, position, id ->
            selectedItem = parent.getItemAtPosition(position).toString()
        }


        val btnReveal = findViewById<Button>(R.id.btnReveal)
        btnReveal.setOnClickListener {
            if (selectedItem != null) {
                val intent = Intent(this, TarotDeckActivity::class.java)
                intent.putExtra("reading", selectedItem)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please select a reading first", Toast.LENGTH_LONG).show()
            }
        }


//        val items = listOf("Option 1", "Option 2", "Option 3")
//        val adapter = ArrayAdapter(this, R.layout.dropdown_item, items)
//        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.dropdownField)
//        autoCompleteTextView.setAdapter(adapter)

    }
}