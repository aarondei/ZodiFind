package cit.edu.zodifind.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.commit
import cit.edu.zodifind.R
import cit.edu.zodifind.activities.astrology.SplashAstrologyActivity
import cit.edu.zodifind.activities.palmistry.SplashPalmistryActivity
import cit.edu.zodifind.activities.tarot.SplashTarotActivity
import cit.edu.zodifind.app.ZodiFindApplication
import cit.edu.zodifind.fragments.MenuFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var burgerMenuIcon: ImageView
    private var selectedImageUri: Uri? = null
    private val EDIT_PROFILE_REQUEST_CODE = 123

    private val pickImageRequest = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        drawerLayout = findViewById(R.id.drawerLayout)
        burgerMenuIcon = findViewById(R.id.burgerMenuIcon)


        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add(R.id.navigationDrawer, MenuFragment.newInstance())
            }
        }

        burgerMenuIcon.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }


        // features
        val btnAstrology = findViewById<ImageView>(R.id.rhombusAstrology)
        val btnTarot = findViewById<ImageView>(R.id.rhombusTarot)
        val btnPalmistry = findViewById<ImageView>(R.id.rhombusPalm)

        btnAstrology.setOnClickListener {
            startActivity(Intent(this, SplashAstrologyActivity:: class.java))
        }
        btnTarot.setOnClickListener {
            startActivity(Intent(this, SplashTarotActivity:: class.java))
        }
        btnPalmistry.setOnClickListener {
            startActivity(Intent(this, SplashPalmistryActivity:: class.java))
        }


    }


    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    @SuppressLint("WrongConstant")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == EDIT_PROFILE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val newName = data?.getStringExtra("newName")
            val newBio = data?.getStringExtra("newBio")
            val imageUriStr = data?.getStringExtra("profileImageUri")

            val user = (application as ZodiFindApplication).currentUser ?: return

            newName?.let {
                user.name = it
            }

            newBio?.let {
                user.bio = it
            }

            imageUriStr?.let {
                selectedImageUri = Uri.parse(it)
                user.profileImageUri = it
            }
        }
        if (resultCode == RESULT_OK && requestCode == pickImageRequest) {
            data?.data?.let { imageUri ->
                val user = (application as ZodiFindApplication).currentUser ?: return
                user.profileImageUri = imageUri.toString()
                selectedImageUri = imageUri

                val flags = data.flags and (Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                contentResolver.takePersistableUriPermission(imageUri, flags)
            }
        }
    }
}