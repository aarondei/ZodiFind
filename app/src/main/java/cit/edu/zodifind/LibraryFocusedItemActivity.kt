package cit.edu.zodifind

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cit.edu.zodifind.fragments.LibraryFocusedItemBirthdateFragment
import cit.edu.zodifind.helpers.ZodiacSign

class LibraryFocusedItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.library_focuseditem)

        val zodiacName = intent.getStringExtra("zodiac")
        val zodiac = ZodiacSign.valueOf(zodiacName!!)

        // TODO fill up the fields using "zodiac"


        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, LibraryFocusedItemBirthdateFragment())
            .commit()
    }
}