package cit.edu.zodifind.data.astrology

import cit.edu.zodifind.R

enum class RulingHouse(val symbolIcon: Int, val description: Int) {
    FIRST(R.drawable.symbol_1, R.string.rulinghouse_first),
    SECOND(R.drawable.symbol_2, R.string.rulinghouse_second),
    THIRD(R.drawable.symbol_3, R.string.rulinghouse_third),
    FOURTH(R.drawable.symbol_4, R.string.rulinghouse_fourth),
    FIFTH(R.drawable.symbol_5, R.string.rulinghouse_fifth),
    SIXTH(R.drawable.symbol_6, R.string.rulinghouse_sixth),
    SEVENTH(R.drawable.symbol_7, R.string.rulinghouse_seventh),
    EIGHTH(R.drawable.symbol_8, R.string.rulinghouse_eighth),
    NINTH(R.drawable.symbol_9, R.string.rulinghouse_ninth),
    TENTH(R.drawable.symbol_10, R.string.rulinghouse_tenth),
    ELEVENTH(R.drawable.symbol_11, R.string.rulinghouse_eleventh),
    TWELFTH(R.drawable.symbol_12, R.string.rulinghouse_twelfth)
}