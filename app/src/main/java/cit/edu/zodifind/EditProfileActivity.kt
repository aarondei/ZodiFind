package cit.edu.zodifind

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.*
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
    private lateinit var btnBack: ImageView
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
            try {
                contentResolver.takePersistableUriPermission(
                    uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            } catch (e: SecurityException) {
                e.printStackTrace()
                Toast.makeText(this, "Can't persist URI permission", Toast.LENGTH_SHORT).show()
            }
            // Load image into ImageView
            loadImageFromUri(uri)


            val user = (application as ZodiFindApplication).currentUser
            user?.profileImageUri = uri.toString()
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
        btnSave = findViewById(R.id.btnSave)
        btnBack = findViewById<ImageView>(R.id.btnBack)

        val name = intent.getStringExtra("name") ?: ""
        val bio = intent.getStringExtra("bio") ?: ""
        val imageUriString = intent.getStringExtra("profileImageUri")

        etEditName.setText(name)
        etEditBio.setText(bio)
        tvNameCharCount.text = "${name.length}/$MAX_NAME_LENGTH"
        tvBioCharCount.text = "${bio.length}/$MAX_BIO_LENGTH"

        imageUriString?.let {
            try {
                val uri = Uri.parse(it)
                selectedImageUri = uri
                loadImageFromUri(uri)
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Cannot load saved image", Toast.LENGTH_SHORT).show()
            }
        }

        etEditName.addTextChangedListener {
            val count = it?.length ?: 0
            tvNameCharCount.text = "$count/$MAX_NAME_LENGTH"
        }

        etEditBio.addTextChangedListener {
            val count = it?.length ?: 0
            tvBioCharCount.text = "$count/$MAX_BIO_LENGTH"
        }

        imgPfp.setOnClickListener {
            openImagePicker()
        }
        imgPfpCam.setOnClickListener {
            openImagePicker()
        }

        btnSave.setOnClickListener {
            saveChanges()
        }

        btnBack.setOnClickListener {
            startActivity(Intent(this, HomeActivity:: class.java))
        }
    }

    private fun openImagePicker() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            pickImageLauncher.launch(
                androidx.activity.result.PickVisualMediaRequest(
                    androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.ImageOnly
                )
            )
        } else {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                pickImageLauncher.launch(
                    androidx.activity.result.PickVisualMediaRequest(
                        androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
            } else {
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
    }

    private fun loadImageFromUri(uri: Uri?) {
        if (uri == null) return
        try {
            contentResolver.openInputStream(uri)?.use {
                val bitmap = BitmapFactory.decodeStream(it)
                imgPfp.setImageBitmap(bitmap)
                imgPfpCam.visibility = ImageView.GONE
            }
        } catch (e: SecurityException) {
            e.printStackTrace()
            Toast.makeText(this, "Image access denied. Please re-select.", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Error loading image", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveChanges() {
        val newName = etEditName.text.toString().trim()
        val newBio = etEditBio.text.toString().trim()

        val app = application as ZodiFindApplication
        val user = app.currentUser ?: return

        user.bio = newBio
        user.name = newName

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
