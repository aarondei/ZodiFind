package cit.edu.zodifind.activities

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import cit.edu.zodifind.R

class FAQActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.faq_zodifind)

        val faqTextView = findViewById<TextView>(R.id.tvQ1)
        faqTextView.text = Html.fromHtml(getString(R.string.faq_text), Html.FROM_HTML_MODE_LEGACY)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener(){
            val intent = Intent(this, SettingsActivity:: class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

    }
}