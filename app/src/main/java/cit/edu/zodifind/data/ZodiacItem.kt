package cit.edu.zodifind.data

import cit.edu.zodifind.helpers.ZodiacSign
import java.time.LocalDate

data class ZodiacItem(
    val name: String,
    val dateRange: String,
    val imageResId: Int
)
