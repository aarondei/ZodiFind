package cit.edu.zodifind.data.astrology

import cit.edu.zodifind.R

enum class Element(val symbolIcon: Int, val description: Int) {
    FIRE(R.drawable.symbol_fire, R.string.element_fire),
    EARTH(R.drawable.symbol_earth, R.string.element_earth),
    AIR(R.drawable.symbol_air, R.string.element_air),
    WATER(R.drawable.symbol_water, R.string.element_water)
}