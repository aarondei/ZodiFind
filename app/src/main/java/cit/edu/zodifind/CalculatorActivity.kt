package cit.edu.zodifind

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import cit.edu.zodifind.app.ZodiFindApplication
import cit.edu.zodifind.fragments.DatePickerFragment
import cit.edu.zodifind.fragments.DatePickerViewModel

class CalculatorActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator)

        val app = application as ZodiFindApplication

        val tvHello = findViewById<TextView>(R.id.tvHello)
        tvHello.text = "${app.currentUser?.name ?: "User"},"

        val fragment = DatePickerFragment()
        fragment.arguments = Bundle().apply {
            putString("MODE", "OBJECT")
        }

        // to import DatePicker
        if (savedInstanceState == null) { // loads the fragment only once
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit()
        }

        val btnProceed = findViewById<Button>(R.id.btnProceed)
        btnProceed.setOnClickListener(){
            startActivity(Intent(this, CalculatorResultActivity:: class.java))

        }

    }
}