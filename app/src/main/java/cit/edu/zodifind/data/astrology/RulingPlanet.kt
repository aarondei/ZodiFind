package cit.edu.zodifind.data.astrology

import cit.edu.zodifind.R

enum class RulingPlanet(val symbolIcon: Int, val description: Int) {
    MERCURY_GEMINI(R.drawable.symbol_mercury, R.string.rulingplanet_mercury_gemini),
    MERCURY_VIRGO(R.drawable.symbol_mercury, R.string.rulingplanet_mercury_virgo),
    VENUS_TAURUS(R.drawable.symbol_venus, R.string.rulingplanet_venus_taurus),
    VENUS_LIBRA(R.drawable.symbol_venus, R.string.rulingplanet_venus_libra),
    MARS(R.drawable.symbol_mars, R.string.rulingplanet_mars),
    JUPITER(R.drawable.symbol_jupiter, R.string.rulingplanet_jupiter),
    SATURN(R.drawable.symbol_saturn, R.string.rulingplanet_saturn),
    URANUS(R.drawable.symbol_uranus, R.string.rulingplanet_uranus),
    NEPTUNE(R.drawable.symbol_neptune, R.string.rulingplanet_neptune),
    PLUTO(R.drawable.symbol_pluto, R.string.rulingplanet_pluto),
    SUN(R.drawable.symbol_sun, R.string.rulingplanet_sun),
    MOON(R.drawable.symbol_moon, R.string.rulingplanet_moon)
}