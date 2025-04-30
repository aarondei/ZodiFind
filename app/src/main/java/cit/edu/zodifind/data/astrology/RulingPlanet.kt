package cit.edu.zodifind.data.astrology

import cit.edu.zodifind.R

enum class RulingPlanet(val symbolIcon: Int, val description: Int) {
    MERCURY_GEMINI(R.drawable.star, R.string.rulingplanet_mercury_gemini),
    MERCURY_VIRGO(R.drawable.star, R.string.rulingplanet_mercury_virgo),
    VENUS_TAURUS(R.drawable.star, R.string.rulingplanet_venus_taurus),
    VENUS_LIBRA(R.drawable.star, R.string.rulingplanet_venus_libra),
    MARS(R.drawable.star, R.string.rulingplanet_mars),
    JUPITER(R.drawable.star, R.string.rulingplanet_jupiter),
    SATURN(R.drawable.star, R.string.rulingplanet_saturn),
    URANUS(R.drawable.star, R.string.rulingplanet_uranus),
    NEPTUNE(R.drawable.star, R.string.rulingplanet_neptune),
    PLUTO(R.drawable.star, R.string.rulingplanet_pluto),
    SUN(R.drawable.star, R.string.rulingplanet_sun),
    MOON(R.drawable.star, R.string.rulingplanet_moon)
}