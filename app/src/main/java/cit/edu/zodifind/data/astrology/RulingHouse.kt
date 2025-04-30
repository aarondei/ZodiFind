package cit.edu.zodifind.data.astrology

import cit.edu.zodifind.R

enum class RulingHouse(val symbolIcon: Int, val description: Int) {
    FIRST(R.drawable.star, R.string.rulinghouse_first),
    SECOND(R.drawable.star, R.string.rulinghouse_second),
    THIRD(R.drawable.star, R.string.rulinghouse_third),
    FOURTH(R.drawable.star, R.string.rulinghouse_fourth),
    FIFTH(R.drawable.star, R.string.rulinghouse_fifth),
    SIXTH(R.drawable.star, R.string.rulinghouse_sixth),
    SEVENTH(R.drawable.star, R.string.rulinghouse_seventh),
    EIGHTH(R.drawable.star, R.string.rulinghouse_eighth),
    NINTH(R.drawable.star, R.string.rulinghouse_ninth),
    TENTH(R.drawable.star, R.string.rulinghouse_tenth),
    ELEVENTH(R.drawable.star, R.string.rulinghouse_eleventh),
    TWELFTH(R.drawable.star, R.string.rulinghouse_twelfth)
}