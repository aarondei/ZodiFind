package cit.edu.zodifind.data.astrology

import cit.edu.zodifind.R

enum class Quality(val symbolIcon: Int, val description: Int) {
    CARDINAL(R.drawable.symbol_cardinal, R.string.quality_cardinal),
    FIXED(R.drawable.symbol_fixed, R.string.quality_fixed),
    MUTABLE(R.drawable.symbol_mutable, R.string.quality_mutable)
}