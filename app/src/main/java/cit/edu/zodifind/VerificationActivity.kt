package cit.edu.zodifind

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cit.edu.zodifind.fragments.DatePicker

class VerificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification_first)

        // Add the DatePicker fragment dynamically
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, DatePicker()) // R.id.fragmentContainer is your FrameLayout or container ID
                .commit()
        }
    }
}