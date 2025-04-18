package cit.edu.zodifind.fragments

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import cit.edu.zodifind.app.ZodiFindApplication
import cit.edu.zodifind.helpers.ZodiacSign
import java.time.LocalDate


class DatePickerViewModel(application: Application) : AndroidViewModel(application) {
    val selectedDate = MutableLiveData<LocalDate>() // viewmodel's reference
    val app = application as ZodiFindApplication

    @RequiresApi(Build.VERSION_CODES.O)
    fun setDate(date: LocalDate) {
        selectedDate.value = date
        app.currentUser?.birthdate = date
        app.currentUser?.zodiacSign = ZodiacSign.parseDate(date)
    }
}