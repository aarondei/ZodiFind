package cit.edu.zodifind.fragments

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import cit.edu.zodifind.app.ZodiFindApplication
import cit.edu.zodifind.data.user.CapturedZodiacTempObject
import cit.edu.zodifind.data.astrology.ZodiacSign
import java.time.LocalDate


class DatePickerViewModel(application: Application) : AndroidViewModel(application) {
    val snappedDate = MutableLiveData<LocalDate>()
    val app = application as ZodiFindApplication

    @RequiresApi(Build.VERSION_CODES.O)
    fun setObjectData(date: LocalDate) {

        snappedDate.value = date
        CapturedZodiacTempObject.capturedSign = ZodiacSign.parseDate(date)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setUserData(date: LocalDate) {

        snappedDate.value = date
        CapturedZodiacTempObject.capturedSign = ZodiacSign.parseDate(date)
        app.currentUser?.birthdate = date
        app.currentUser?.zodiacSign = ZodiacSign.parseDate(date)
    }
}