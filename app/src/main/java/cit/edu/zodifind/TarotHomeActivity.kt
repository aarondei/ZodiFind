package cit.edu.zodifind

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout

class TarotHomeActivity : BaseActivity() {

    private var selectedItem: String? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tarot_home)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

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

        val burgerMenuIcon = findViewById<ImageView>(R.id.burgerMenuIcon)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        burgerMenuIcon.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }

    }
}