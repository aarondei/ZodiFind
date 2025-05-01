package cit.edu.zodifind

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException

class ProfileActivity : AppCompatActivity() {
    private lateinit var tvName: TextView
    private lateinit var tvBio: TextView
    private lateinit var imgPfp: ImageView
    private val EDIT_PROFILE_REQUEST_CODE = 123
    private var selectedImageUri: Uri? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        // Initialize UI elements
        tvName = findViewById(R.id.tvName)
        tvBio = findViewById(R.id.tvBio)
        imgPfp = findViewById(R.id.imgPfp)

        // Get initial username from intent
        intent?.let {
            it.getStringExtra("username")?.let { username ->
                tvName.text = username
            }
        }

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }

        val btnEditProfile = findViewById<ImageView>(R.id.imgToEdit)
        btnEditProfile.setOnClickListener {
            // pass current data
            val intent = Intent(this, EditProfileActivity::class.java)
            intent.putExtra("username", tvName.text.toString())
            intent.putExtra("bio", tvBio.text.toString())
            // Pass the image URI string if it exists
            if (selectedImageUri != null) {
                intent.putExtra("profileImageUri", selectedImageUri.toString())
            }
            startActivityForResult(intent, EDIT_PROFILE_REQUEST_CODE)
        }

        val btnEditPfp = findViewById<ImageView>(R.id.imgEditPic)
        btnEditPfp.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            intent.putExtra("username", tvName.text.toString())
            intent.putExtra("bio", tvBio.text.toString())
            if (selectedImageUri != null) {
                intent.putExtra("profileImageUri", selectedImageUri.toString())
            }
            startActivityForResult(intent, EDIT_PROFILE_REQUEST_CODE)
        }

        val btnEditBio = findViewById<ImageView>(R.id.imgEditBio)
        btnEditBio.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            intent.putExtra("username", tvName.text.toString())
            intent.putExtra("bio", tvBio.text.toString())
            if (selectedImageUri != null) {
                intent.putExtra("profileImageUri", selectedImageUri.toString())
            }
            startActivityForResult(intent, EDIT_PROFILE_REQUEST_CODE)
        }
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EDIT_PROFILE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // Get the updated data from the intent
            val newName = data?.getStringExtra("newName")
            val newBio = data?.getStringExtra("newBio")
            val imageUriString = data?.getStringExtra("profileImageUri")

            // Update the views with the new data
            newName?.let { tvName.text = it }
            newBio?.let { tvBio.text = it }

            // Load the new profile picture if a new one was selected
            if (imageUriString != null) {
                selectedImageUri = Uri.parse(imageUriString)
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImageUri)
                    imgPfp.setImageBitmap(bitmap)
                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(this, "Failed to load new profile picture", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }
}

