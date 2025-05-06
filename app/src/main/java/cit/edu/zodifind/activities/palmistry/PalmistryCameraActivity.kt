package cit.edu.zodifind.activities.palmistry

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import cit.edu.zodifind.activities.BaseActivity
import cit.edu.zodifind.R

class PalmistryCameraActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.palmistrycam)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            startActivity(Intent(this, PalmistryLibraryActivity::class.java))
        }
    }
}