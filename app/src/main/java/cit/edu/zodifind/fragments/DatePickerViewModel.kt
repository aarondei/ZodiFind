package cit.edu.zodifind.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate

class DatePickerViewModel : ViewModel() {
    val selectedDate = MutableLiveData<LocalDate>()
}