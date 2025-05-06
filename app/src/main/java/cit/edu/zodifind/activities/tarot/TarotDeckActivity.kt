package cit.edu.zodifind.activities.tarot

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import cit.edu.zodifind.activities.BaseActivity
import cit.edu.zodifind.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TarotDeckActivity : BaseActivity() {

    private  lateinit var selectedReading: String
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.empty_tarot_deck)


        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        }

    }
}