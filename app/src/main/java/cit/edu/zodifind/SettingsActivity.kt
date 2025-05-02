package cit.edu.zodifind

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
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
        val tvBirth = findViewById<TextView>(R.id.tvBirth)
        val profilePic = findViewById<de.hdodenhof.circleimageview.CircleImageView>(R.id.profilepic)

        val app = application as ZodiFindApplication
        app.currentUser?.let { currentUser ->
            tvName.text = currentUser.name
            tvBirth.text = currentUser.birthdate.toString() // Ensure this is properly formatted

            currentUser.profileImageUri?.let { uriString ->
                try {
                    val uri = Uri.parse(uriString)
                    contentResolver.openInputStream(uri)?.use { inputStream ->
                        val bitmap = BitmapFactory.decodeStream(inputStream)
                        profilePic.setImageBitmap(bitmap)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    profilePic.setImageResource(R.drawable.pfp_default)
                }
            } ?: profilePic.setImageResource(R.drawable.pfp_default)
        }

        findViewById<ImageView>(R.id.btnToEdit).setOnClickListener {
            app.currentUser?.let { currentUser ->
                val intent = Intent(this, EditProfileActivity::class.java).apply {
                    putExtra("name", currentUser.name)
                    putExtra("bio", currentUser.bio ?: "")
                    putExtra("username", currentUser.username)
                    currentUser.profileImageUri?.let {
                        putExtra("profileImageUri", it)
                    }
                }
                startActivity(intent)
            }
        }

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        findViewById<ImageView>(R.id.btnToAbout).setOnClickListener {
            startActivity(Intent(this, AboutZodifindActivity::class.java))
        }

        findViewById<ImageView>(R.id.btnToDev).setOnClickListener {
            startActivity(Intent(this, DeveloperActivity::class.java))
        }

        findViewById<ImageView>(R.id.btnToFaq).setOnClickListener {
            startActivity(Intent(this, FaqActivity::class.java))
        }
    }
}
