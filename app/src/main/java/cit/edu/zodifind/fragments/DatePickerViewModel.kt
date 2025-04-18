package cit.edu.zodifind.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cit.edu.zodifind.data.BirthdateHolder
import java.time.LocalDate


class DatePickerViewModel : ViewModel() {
    val selectedDate = MutableLiveData<LocalDate>()

    fun setDate(date: LocalDate) {
        selectedDate.value = date
        BirthdateHolder.birthdate = date
    }
}