package cit.edu.zodifind

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import cit.edu.zodifind.app.ZodiFindApplication

class SettingsActivity : BaseActivity() {
    @SuppressLint("MissingInflatedId")
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)

        val tvName = findViewById<TextView>(R.id.tvName)

        intent?.let {
            it.getStringExtra("name")?.let{name ->
                tvName.text = name
            }
        }

        val btnEdit = findViewById<ImageView>(R.id.btnToEdit)
        btnEdit.setOnClickListener {
            val app = application as ZodiFindApplication
            app.currentUser?.let { currentUser ->
                val intent = Intent(this, EditProfileActivity::class.java)
                intent.putExtra("name", currentUser.name)
                intent.putExtra("bio", currentUser.bio ?: "")
                intent.putExtra("username", currentUser.username)
                currentUser.profileImageUri?.let {
                    intent.putExtra("profileImageUri", it)
                }
                startActivity(intent)
            }
        }

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {

            val intent = Intent(this, HomeActivity:: class.java)
            startActivity(intent)
//            val resultIntent = Intent()
//            resultIntent.putExtra("username", tvName.text.toString())
//            setResult(RESULT_OK, resultIntent)
//            finish()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        val btnAbout = findViewById<ImageView>(R.id.btnToAbout)
        btnAbout.setOnClickListener(){
            val intent = Intent(this, AboutZodifindActivity:: class.java)
            startActivity(intent)
        }

        val btnDev = findViewById<ImageView>(R.id.btnToDev)
        btnDev.setOnClickListener(){
            val intent = Intent(this, DeveloperActivity:: class.java)
            startActivity(intent)
        }

        val btnFAQ = findViewById<ImageView>(R.id.btnToFaq)
        btnFAQ.setOnClickListener(){
            val intent = Intent(this, FaqActivity:: class.java)
            startActivity(intent)
        }


    }
}