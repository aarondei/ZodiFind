package cit.edu.zodifind

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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

        // Try getting data from Intent extras first
        val intentName = intent.getStringExtra("name")
        val intentBday = intent.getStringExtra("bday")
        val intentImageUri = intent.getStringExtra("profileImageUri")

        // Display name
        tvName.text = intentName ?: app.currentUser?.name ?: ""

        // Display birthdate
        tvBirth.text = intentBday ?: app.currentUser?.birthdate?.toString() ?: ""

        // Load image from Intent URI if available, fallback to app.currentUser
        if (!intentImageUri.isNullOrEmpty()) {
            try {
                val uri = Uri.parse(intentImageUri)
                contentResolver.openInputStream(uri)?.use { inputStream ->
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    profilePic.setImageBitmap(bitmap)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                profilePic.setImageResource(R.drawable.pfp_default)
            }
        } else {
            app.currentUser?.profileImageUri?.let { uriString ->
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
//        changing password

        val tvChangePassword = findViewById<TextView>(R.id.tvChange)
        tvChangePassword.setOnClickListener {
            showChangePasswordDialog()
        }
    }

    private fun showChangePasswordDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Change Password")


        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        dialogBuilder.setView(input)


        dialogBuilder.setPositiveButton("Change") { _, _ ->
            val newPassword = input.text.toString().trim()
            if (newPassword.isNotEmpty()) {
                changePassword(newPassword)
            } else {
                Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
        dialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        
        dialogBuilder.show()
    }

    private fun changePassword(newPassword: String) {

        val app = application as ZodiFindApplication
        val user = app.currentUser ?: return

        // Update the password
        user.password = newPassword // Save this new password

        // Show confirmation message
        Toast.makeText(this, "Password changed successfully", Toast.LENGTH_SHORT).show()

    }
}
