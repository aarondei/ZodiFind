package cit.edu.zodifind.data

import cit.edu.zodifind.helpers.ZodiacSign
import java.util.Date

data class User( // TODO SERIALIZE
    val name: String,
    val username: String,
    val password: String,
    val birthdate: Date? = null,
    val zodiacSign: ZodiacSign? = null
)
