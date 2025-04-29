package cit.edu.zodifind.data.astrology

import cit.edu.zodifind.R

enum class Element(val symbolIcon: Int, val description: Int) {
    FIRE(R.drawable.glowing_star, R.string.element_fire),
    EARTH(R.drawable.glowing_star, R.string.element_earth),
    AIR(R.drawable.glowing_star, R.string.element_air),
    WATER(R.drawable.glowing_star, R.string.element_water)
}