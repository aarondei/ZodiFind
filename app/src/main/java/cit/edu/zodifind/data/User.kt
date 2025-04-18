package cit.edu.zodifind.data

import cit.edu.zodifind.helpers.ZodiacSign
import java.time.LocalDate

data class User(
    val name: String,
    val username: String,
    val password: String,
    var birthdate: LocalDate? = null,
    var zodiacSign: ZodiacSign? = null
)
