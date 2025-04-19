package cit.edu.zodifind

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cit.edu.zodifind.fragments.LibraryFocusedItemBirthdateFragment

class LibBirthdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.library_focuseditem_birthdate)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, LibraryFocusedItemBirthdateFragment())
            .commit()
    }
}