package cit.edu.zodifind.data

import cit.edu.zodifind.data.astrology.ZodiacSign
import java.time.LocalDate

data class User(
    var name: String,
    var username: String,
    var password: String,
    var birthdate: LocalDate? = null,
    var bio: String? = null,
    var profileImageUri: String? = null,
    var zodiacSign: ZodiacSign? = null
)
