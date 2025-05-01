package cit.edu.zodifind

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import java.io.IOException

class EditProfileActivity : AppCompatActivity() {

    private lateinit var etEditName: EditText
    private lateinit var etEditBio: EditText
    private lateinit var tvNameCharCount: TextView
    private lateinit var tvBioCharCount: TextView
    private lateinit var imgPfp: ImageView
    private lateinit var imgPfpCam: ImageView
    private lateinit var btnSave: Button
    private var selectedImageUri: Uri? = null
    private val MAX_NAME_LENGTH = 15
    private val MAX_BIO_LENGTH = 150
    private val READ_EXTERNAL_STORAGE_REQUEST = 101

    // Permission request launcher
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Permission granted, load image if we have one
            loadProfileImage()
        } else {
            Toast.makeText(
                this,
                "Storage permission is required to load profile images",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    // ActivityResultLauncher for handling image selection
    private val selectImageLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                if (data != null && data.data != null) {
                    selectedImageUri = data.data

                    // Take persistent URI permission
                    try {
                        selectedImageUri?.let { uri ->
                            contentResolver.takePersistableUriPermission(
                                uri,
                                Intent.FLAG_GRANT_READ_URI_PERMISSION
                            )
                        }
                    } catch (e: SecurityException) {
                        // This might not work for all URIs, but that's okay
                    }

                    // Load the image
                    loadImageFromUri(selectedImageUri)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_profile)

        // Initialize UI elements
        etEditName = findViewById(R.id.etEditName)
        etEditBio = findViewById(R.id.etEditBio)
        tvNameCharCount = findViewById(R.id.tvNameCharCount)
        tvBioCharCount = findViewById(R.id.tvBioCharCount)
        imgPfp = findViewById(R.id.imgPfp)
        imgPfpCam = findViewById(R.id.imgPfpCam)
        btnSave = findViewById(R.id.Cancel)

        // Load existing data (if any)
        intent?.let {
            it.getStringExtra("username")?.let { username ->
                etEditName.setText(username)
            }
            it.getStringExtra("bio")?.let { bio ->
                etEditBio.setText(bio)
            }

            // Initial setup for character counts
            tvNameCharCount.text = "${etEditName.text.length}/$MAX_NAME_LENGTH"
            tvBioCharCount.text = "${etEditBio.text.length}/$MAX_BIO_LENGTH"

            // Get image URI but load it after permissions check
            val imageUriString = it.getStringExtra("profileImageUri")
            if (!imageUriString.isNullOrEmpty()) {
                selectedImageUri = Uri.parse(imageUriString)
            }
        }

        // Check permissions and load profile image if we have one
        checkPermissionsAndLoadImage()

        // Set up text change listeners for character count updates and input validation
        etEditName.addTextChangedListener { text ->
            val nameLength = text?.length ?: 0
            tvNameCharCount.text = "$nameLength/$MAX_NAME_LENGTH"
            if (nameLength > MAX_NAME_LENGTH) {
                tvNameCharCount.setTextColor(ContextCompat.getColor(this, R.color.red))
            } else {
                tvNameCharCount.setTextColor(ContextCompat.getColor(this, R.color.white))
            }
        }

        etEditBio.addTextChangedListener { text ->
            val bioLength = text?.length ?: 0
            tvBioCharCount.text = "$bioLength/$MAX_BIO_LENGTH"
            if (bioLength > MAX_BIO_LENGTH) {
                tvBioCharCount.setTextColor(ContextCompat.getColor(this, R.color.red))
            } else {
                tvBioCharCount.setTextColor(ContextCompat.getColor(this, R.color.white))
            }
        }

        // Set click listener for the profile picture change
        imgPfp.setOnClickListener {
            openImagePicker()
        }

        imgPfpCam.setOnClickListener {
            openImagePicker()
        }

        // Set click listener for the save button
        btnSave.setOnClickListener {
            saveChanges()
        }

        //back button
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun checkPermissionsAndLoadImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Android 13+ uses more granular READ_MEDIA_IMAGES permission
            loadProfileImage() // No permission needed for previously selected URIs
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                loadProfileImage()
            } else {
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        } else {
            // Below Android 6, permissions are granted at install time
            loadProfileImage()
        }
    }

    private fun loadProfileImage() {
        selectedImageUri?.let {
            loadImageFromUri(it)
        }
    }

    private fun loadImageFromUri(uri: Uri?) {
        if (uri == null) return

        try {
            // Use try/catch for potential SecurityException
            contentResolver.openInputStream(uri).use { inputStream ->
                if (inputStream != null) {
                    val bitmap = android.graphics.BitmapFactory.decodeStream(inputStream)
                    imgPfp.setImageBitmap(bitmap)
                    imgPfpCam.visibility = ImageView.GONE
                } else {
                    Toast.makeText(this, "Failed to open image stream", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: SecurityException) {
            Toast.makeText(
                this,
                "Permission denied: ${e.message}",
                Toast.LENGTH_SHORT
            ).show()
        } catch (e: IOException) {
            Toast.makeText(
                this,
                "Failed to load image: ${e.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"
            addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        selectImageLauncher.launch(intent)
    }

    private fun saveChanges() {
        val newName = etEditName.text.toString().trim()
        val newBio = etEditBio.text.toString().trim()

        if (newName.length > MAX_NAME_LENGTH) {
            Toast.makeText(this, "Name cannot exceed $MAX_NAME_LENGTH characters", Toast.LENGTH_SHORT).show()
            return
        }
        if (newBio.length > MAX_BIO_LENGTH) {
            Toast.makeText(this, "Bio cannot exceed $MAX_BIO_LENGTH characters", Toast.LENGTH_SHORT).show()
            return
        }

        // Prepare the result intent
        val resultIntent = Intent()
        resultIntent.putExtra("newName", newName)
        resultIntent.putExtra("newBio", newBio)
        if (selectedImageUri != null) {
            resultIntent.putExtra("profileImageUri", selectedImageUri.toString())
        }

        setResult(RESULT_OK, resultIntent)
        finish()
    }
}