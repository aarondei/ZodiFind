package cit.edu.zodifind

import android.annotation.SuppressLint
import android.app.Activity
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
import androidx.activity.result.contract.ActivityResultContracts
import cit.edu.zodifind.app.ZodiFindApplication
import java.io.IOException

class SettingsActivity : BaseActivity() {
    var selectedImageUri: Uri? = null
    val pickImageRequest = 100
    val EDIT_PROFILE_REQUEST_CODE = 123


    @SuppressLint("MissingInflatedId")
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)

        val tvName = findViewById<TextView>(R.id.tvName)
        val tvBirth = findViewById<TextView>(R.id.tvBirth)
        val profilePic = findViewById<de.hdodenhof.circleimageview.CircleImageView>(R.id.profilepic)

        val app = application as ZodiFindApplication

        val intentName = intent.getStringExtra("name")
        val intentBday = intent.getStringExtra("bday")
        val intentImageUri = intent.getStringExtra("profileImageUri")

        tvName.text = intentName ?: app.currentUser?.name ?: ""

        tvBirth.text = intentBday ?: app.currentUser?.birthdate?.toString() ?: ""

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
                startActivityForResult(intent, EDIT_PROFILE_REQUEST_CODE)
            }
        }

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
            startActivity(Intent(this, HomeActivity::class.java))
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

        user.password = newPassword
        Toast.makeText(this, "Password changed successfully", Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == EDIT_PROFILE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val newName = data?.getStringExtra("newName")
            val newBio = data?.getStringExtra("newBio")
            val imageUriStr = data?.getStringExtra("profileImageUri")

            val user = (application as ZodiFindApplication).currentUser ?: return

            newName?.let {
                user.name = it
                findViewById<TextView>(R.id.tvName).text = it
            }

            newBio?.let {
                user.bio = it
            }

            imageUriStr?.let {
                val uri = Uri.parse(it)
                user.profileImageUri = it
                val profilePic = findViewById<de.hdodenhof.circleimageview.CircleImageView>(R.id.profilepic)
                try {
                    contentResolver.openInputStream(uri)?.use { stream ->
                        val bitmap = BitmapFactory.decodeStream(stream)
                        profilePic.setImageBitmap(bitmap)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    profilePic.setImageResource(R.drawable.pfp_default)
                }
            }
        }

        if (resultCode == RESULT_OK && requestCode == pickImageRequest) {
            data?.data?.let { imageUri ->
                val user = (application as ZodiFindApplication).currentUser ?: return
                user.profileImageUri = imageUri.toString()

                val flags = data.flags and (Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                contentResolver.takePersistableUriPermission(imageUri, flags)
            }
        }
    }
}
