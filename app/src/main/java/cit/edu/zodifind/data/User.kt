package cit.edu.zodifind.data

import cit.edu.zodifind.helpers.ZodiacSign
import java.time.LocalDate

data class User( // TODO SERIALIZE
    val name: String,
    val username: String,
    val password: String,
    var birthdate: LocalDate? = null,
    var zodiacSign: ZodiacSign? = null
)
