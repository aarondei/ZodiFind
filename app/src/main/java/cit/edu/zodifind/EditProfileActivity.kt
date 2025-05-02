package cit.edu.zodifind

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import cit.edu.zodifind.app.ZodiFindApplication
import java.io.IOException

class EditProfileActivity : BaseActivity() {

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


    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) openImagePicker()
        else Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
    }

    private val pickImageLauncher = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            selectedImageUri = uri
            loadImageFromUri(uri)
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_profile)

        etEditName = findViewById(R.id.etEditName)
        etEditBio = findViewById(R.id.etEditBio)
        tvNameCharCount = findViewById(R.id.tvNameCharCount)
        tvBioCharCount = findViewById(R.id.tvBioCharCount)
        imgPfp = findViewById(R.id.imgPfp)
        imgPfpCam = findViewById(R.id.imgPfpCam)
        btnSave = findViewById(R.id.btnSave) // FIXED ID!

        val name = intent.getStringExtra("name") ?: ""
        val bio = intent.getStringExtra("bio") ?: ""
        val imageUriString = intent.getStringExtra("profileImageUri")

        etEditName.setText(name)
        etEditBio.setText(bio)
        tvNameCharCount.text = "${name.length}/$MAX_NAME_LENGTH"
        tvBioCharCount.text = "${bio.length}/$MAX_BIO_LENGTH"

        imageUriString?.let {
            selectedImageUri = Uri.parse(it)
            loadImageFromUri(selectedImageUri)
        }

        etEditName.addTextChangedListener {
            val count = it?.length ?: 0
            tvNameCharCount.text = "$count/$MAX_NAME_LENGTH"
        }

        etEditBio.addTextChangedListener {
            val count = it?.length ?: 0
            tvBioCharCount.text = "$count/$MAX_BIO_LENGTH"
        }

        imgPfp.setOnClickListener { openImagePicker() }
        imgPfpCam.setOnClickListener { openImagePicker() }

        btnSave.setOnClickListener { saveChanges() }

        findViewById<ImageView>(R.id.btnBack).setOnClickListener { finish() }
    }

    private fun openImagePicker() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            pickImageLauncher.launch(androidx.activity.result.PickVisualMediaRequest(androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.ImageOnly))
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                pickImageLauncher.launch(androidx.activity.result.PickVisualMediaRequest(androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.ImageOnly))
            } else {
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
    }

    private fun loadImageFromUri(uri: Uri?) {
        if (uri == null) return
        try {
            contentResolver.openInputStream(uri)?.use {
                val bitmap = android.graphics.BitmapFactory.decodeStream(it)
                imgPfp.setImageBitmap(bitmap)
                imgPfpCam.visibility = ImageView.GONE
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun saveChanges() {
        val newName = etEditName.text.toString().trim()
        val newBio = etEditBio.text.toString().trim()

        if (newName.length > MAX_NAME_LENGTH || newBio.length > MAX_BIO_LENGTH) {
            Toast.makeText(this, "Name or bio too long", Toast.LENGTH_SHORT).show()
            return
        }

        val resultIntent = Intent().apply {
            putExtra("newName", newName)
            putExtra("newBio", newBio)
            selectedImageUri?.let { putExtra("profileImageUri", it.toString()) }
        }

        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }
}
