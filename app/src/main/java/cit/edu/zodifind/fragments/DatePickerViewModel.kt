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
    val snappedDate = MutableLiveData<LocalDate>() // TODO DELETE IF NO USE ANYMORE
    val app = application as ZodiFindApplication

    // TODO FIX TRANSFERRING OF DATA

    @RequiresApi(Build.VERSION_CODES.O)
    fun setDate(date: LocalDate) {

        snappedDate.value = date
    }
    fun setObjectData(date: LocalDate) {

        snappedDate.value = date
        CapturedBirthdate.capturedDate = date
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setUserData(date: LocalDate) {

        Log.d("DATE", "${snappedDate.value}")

        snappedDate.value = date
        CapturedBirthdate.capturedDate = date
        app.currentUser?.birthdate = date
        app.currentUser?.zodiacSign = ZodiacSign.parseDate(date)
    }
}