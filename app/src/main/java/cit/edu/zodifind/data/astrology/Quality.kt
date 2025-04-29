package cit.edu.zodifind.data.astrology

import cit.edu.zodifind.R

enum class Quality(val symbolIcon: Int, val description: Int) {
    CARDINAL(R.drawable.star, R.string.quality_cardinal),
    FIXED(R.drawable.star, R.string.quality_fixed),
    MUTABLE(R.drawable.star, R.string.quality_mutable)
}