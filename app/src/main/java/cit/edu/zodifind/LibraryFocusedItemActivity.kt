package cit.edu.zodifind

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import cit.edu.zodifind.data.CapturedZodiacTempObject
import cit.edu.zodifind.data.astrology.ZodiacSign
import cit.edu.zodifind.fragments.LibraryFocusedItemFragment

class LibraryFocusedItemActivity : BaseActivity() {

    private lateinit var key: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.library_focuseditem)

        intent?.let {
            key = it.getStringExtra("zodiac").toString()
        }

        val zodiacSign = ZodiacSign.valueOf(key)

        //CapturedZodiacTempObject.capturedSign?.let { imgSketch.setImageResource(it.sketchIcon) }

        val fragment = LibraryFocusedItemFragment()
        val bundle = Bundle()
        bundle.putString("key", key)
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.libraryFocusedItemFragment, fragment)
            .commit()
    }
}