package cit.edu.zodifind

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cit.edu.zodifind.app.ZodiFindApplication
import de.hdodenhof.circleimageview.CircleImageView
import java.io.File
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

        val app = application as ZodiFindApplication
        val user = app.currentUser ?: return

        // Set user info
        tvName.text = user.name
        tvUsername.text = "@${user.username}"
        tvBio.text = user.bio ?: ""
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

        // Button click listeners
        findViewById<ImageView>(R.id.btnBack).setOnClickListener { finish() }
        findViewById<ImageView>(R.id.imgEditBio).setOnClickListener { launchEdit(user) }
        findViewById<ImageView>(R.id.imgToEdit).setOnClickListener { launchEdit(user) }

        val toEditPic = findViewById<ImageView>(R.id.imgEditPic)
        toEditPic.setOnClickListener {
            // Create an intent to open the gallery
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, pickImageRequest)
        }
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
                val file = File(selectedImageUri!!.path!!)
                if (file.exists()) {
                    imgPfp.setImageURI(selectedImageUri)
                } else {
                    imgPfp.setImageResource(R.drawable.pfp_default)
                }
            } catch (e: Exception) {
                imgPfp.setImageResource(R.drawable.pfp_default)
            }
        } ?: imgPfp.setImageResource(R.drawable.pfp_default)
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
                imgPfp.setImageURI(imageUri)  // Set the selected image to the ImageView
            }
        }
    }
}
