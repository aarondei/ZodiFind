package cit.edu.zodifind

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.InputType
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cit.edu.zodifind.app.ZodiFindApplication
import de.hdodenhof.circleimageview.CircleImageView
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class ProfileActivity : BaseActivity() {

    private lateinit var tvName: TextView
    private lateinit var tvUsername: TextView
    private lateinit var tvBio: TextView
    private lateinit var tvBirth: TextView
    private lateinit var tvSign: TextView
    private lateinit var imgPfp: CircleImageView
    private var selectedImageUri: Uri? = null

    private val EDIT_PROFILE_REQUEST_CODE = 123
    private val pickImageRequest = 100

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        // Initialize views
        tvName = findViewById(R.id.tvName)
        tvUsername = findViewById(R.id.tvUsername)
        tvBio = findViewById(R.id.tvBio)
        tvBirth = findViewById(R.id.tvBday)
        tvSign = findViewById(R.id.tvSign)
        imgPfp = findViewById(R.id.imgPfp)

        val btnBack = findViewById<ImageView>(R.id.btnBack)

        val app = application as ZodiFindApplication
        val user = app.currentUser ?: return

        // Set user info
        updateUserInfo(user)

        // Edit profile picture
        findViewById<ImageView>(R.id.imgEditPic).setOnClickListener {
            openImagePicker()
        }

        // Edit bio
        findViewById<ImageView>(R.id.imgEditBio).setOnClickListener {
            launchEdit(user)
        }

        // Go to settings
        findViewById<ImageView>(R.id.btnToSettings).setOnClickListener {
            navigateToSettings(user)
        }
        findViewById<TextView>(R.id.tvSettings).setOnClickListener {
            navigateToSettings(user)
        }

        // Change password
        findViewById<TextView>(R.id.tvChange).setOnClickListener {
            showChangePasswordDialog()
        }
        findViewById<ImageView>(R.id.btnToChangePassword).setOnClickListener {
            showChangePasswordDialog()
        }

        btnBack.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    @SuppressLint("NewApi")
    private fun updateUserInfo(user: cit.edu.zodifind.data.User) {
        tvName.text = user.name
        tvUsername.text = "@${user.username}"
        tvBio.text = user.bio ?: "Edit profile to add bio."
        tvSign.text = user.zodiacSign?.name ?: ""

        // Format and show birthdate
        user.birthdate?.let {
            try {
                val parsedDate = LocalDate.parse(it.toString()) // Format: "yyyy-MM-dd"
                val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.getDefault())
                tvBirth.text = parsedDate.format(formatter)
            } catch (e: Exception) {
                tvBirth.text = ""
            }
        }

        loadProfilePicture(user)
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or
                    Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION
        }
        startActivityForResult(intent, pickImageRequest)

    }

    private fun launchEdit(user: cit.edu.zodifind.data.User) {
        val intent = Intent(this, EditProfileActivity::class.java).apply {
            putExtra("name", user.name)
            putExtra("bio", user.bio)
            putExtra("username", user.username)
            putExtra("profileImageUri", user.profileImageUri)
        }
        startActivityForResult(intent, EDIT_PROFILE_REQUEST_CODE)
    }

    private fun loadProfilePicture(user: cit.edu.zodifind.data.User) {
        user.profileImageUri?.let {
            try {
                selectedImageUri = Uri.parse(it)
                contentResolver.openInputStream(selectedImageUri!!)?.use { inputStream ->
                    val bitmap = android.graphics.BitmapFactory.decodeStream(inputStream)
                    imgPfp.setImageBitmap(bitmap)
                } ?: imgPfp.setImageResource(R.drawable.pfp_default)
            } catch (e: IOException) {
                e.printStackTrace()
                imgPfp.setImageResource(R.drawable.pfp_default)
            }
        } ?: imgPfp.setImageResource(R.drawable.pfp_default)
    }

    private fun navigateToSettings(user: cit.edu.zodifind.data.User) {
        val intent = Intent(this, SettingsActivity::class.java).apply {
            putExtra("name", user.name)
            putExtra("bio", user.bio)
            putExtra("username", user.username)
            putExtra("bday", user.birthdate?.toString())
            putExtra("profileImageUri", user.profileImageUri)
        }
        startActivity(intent)
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

    @SuppressLint("WrongConstant")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val uri = data?.data // URI of the selected image
        if (uri != null) {
            try {
                contentResolver.takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            } catch (e: SecurityException) {
                e.printStackTrace()
                Toast.makeText(this, "Permission denied to access the image", Toast.LENGTH_SHORT).show()
            }
        }

        if (requestCode == EDIT_PROFILE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val newName = data?.getStringExtra("newName")
            val newBio = data?.getStringExtra("newBio")
            val imageUriStr = data?.getStringExtra("profileImageUri")

            val user = (application as ZodiFindApplication).currentUser ?: return

            newName?.let {
                user.name = it
                tvName.text = it
            }

            newBio?.let {
                user.bio = it
                tvBio.text = it
            }

            imageUriStr?.let {
                selectedImageUri = Uri.parse(it)
                user.profileImageUri = it
                imgPfp.setImageURI(selectedImageUri)
            }
        }

        if (resultCode == RESULT_OK && requestCode == pickImageRequest) {
            data?.data?.let { imageUri ->
                val user = (application as ZodiFindApplication).currentUser ?: return
                user.profileImageUri = imageUri.toString()
                selectedImageUri = imageUri
                imgPfp.setImageURI(imageUri)

                val flags = data.flags and (Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                contentResolver.takePersistableUriPermission(imageUri, flags)
            }
        }
    }
}
