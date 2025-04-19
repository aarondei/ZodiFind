package cit.edu.zodifind.fragments

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import cit.edu.zodifind.app.ZodiFindApplication
import cit.edu.zodifind.data.CapturedBirthdate
import cit.edu.zodifind.helpers.ZodiacSign
import java.time.LocalDate


class DatePickerViewModel(application: Application) : AndroidViewModel(application) {
    val snappedDate = MutableLiveData<LocalDate>()
    val app = application as ZodiFindApplication

    fun setObjectData(date: LocalDate) {

        snappedDate.value = date
        CapturedBirthdate.capturedDate = date
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setUserData(date: LocalDate) {

        snappedDate.value = date
        CapturedBirthdate.capturedDate = date
        app.currentUser?.birthdate = date
        app.currentUser?.zodiacSign = ZodiacSign.parseDate(date)
    }
}