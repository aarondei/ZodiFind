package cit.edu.zodifind.data.astrology

import android.os.Build
import androidx.annotation.RequiresApi
import cit.edu.zodifind.R
import java.time.LocalDate

@Suppress("DEPRECATION")
enum class ZodiacSign(val faceIcon: Int, val starIcon: Int, val representation: String, val startDate: String, val endDate: String, val sketchIcon: Int, val symbolIcon: Int, val element: Element, val quality: Quality, val rulingPlanet: RulingPlanet, val rulingHouse: RulingHouse, val compatibility: String, val storyTitle: Int, val storyContent: Int, val astronomicalFacts: Int, val symbolismsFacts: Int, val personalityFacts: Int) {
    ARIES(R.drawable.aries_icon, R.drawable.aries_cons, "The Ram","MAR 21", "APR 19", R.drawable.sketch_aries, R.drawable.symbol_aries, Element.FIRE, Quality.CARDINAL, RulingPlanet.MARS, RulingHouse.FIRST, "LEO", R.string.aries_storyTitle, R.string.aries_storyContent, R.string.aries_astronomicalFacts, R.string.aries_symbolismsFacts, R.string.aries_personalityFacts),
    TAURUS(R.drawable.taurus_icon, R.drawable.taurus_cons,"The Bull","APR 20", "MAY 20",  R.drawable.sketch_taurus, R.drawable.symbol_taurus, Element.EARTH, Quality.FIXED, RulingPlanet.VENUS_TAURUS, RulingHouse.SECOND, "CAPRICORN", R.string.taurus_storyTitle, R.string.taurus_storyContent, R.string.taurus_astronomicalFacts, R.string.taurus_symbolismsFact, R.string.taurus_personalityFacts),
    GEMINI(R.drawable.gemini_icon, R.drawable.gemini_cons,"The Twins","MAY 21", "JUN 20",  R.drawable.sketch_gemini, R.drawable.symbol_gemini, Element.AIR, Quality.MUTABLE, RulingPlanet.MERCURY_GEMINI, RulingHouse.THIRD, "LIBRA", R.string.gemini_storyTitle, R.string.gemini_storyContent, R.string.gemini_astronomicalFacts, R.string.gemini_symbolismsFact, R.string.gemini_personalityFacts),
    CANCER(R.drawable.cancer_icon, R.drawable.cancer_cons,"The Crab","JUN 21", "JUL 22",  R.drawable.sketch_cancer, R.drawable.symbol_cancer, Element.WATER, Quality.CARDINAL, RulingPlanet.MOON, RulingHouse.FOURTH, "SCORPIO", R.string.cancer_storyTitle, R.string.cancer_storyContent, R.string.cancer_astronomicalFacts, R.string.cancer_symbolismsFact, R.string.cancer_personalityFacts),
    LEO(R.drawable.leo_icon, R.drawable.leo_cons,"The Lion","JUL 23", "AUG 22",  R.drawable.sketch_leo, R.drawable.symbol_leo, Element.FIRE, Quality.FIXED, RulingPlanet.SUN, RulingHouse.FIFTH, "SAGITTARIUS", R.string.leo_storyTitle, R.string.leo_storyContent, R.string.leo_astronomicalFacts, R.string.leo_symbolismsFact, R.string.leo_personalityFacts),
    VIRGO(R.drawable.virgo_icon, R.drawable.virgo_cons,"The Maiden","AUG 23", "SEPT 22",  R.drawable.sketch_virgo, R.drawable.symbol_virgo, Element.EARTH, Quality.MUTABLE, RulingPlanet.MERCURY_VIRGO, RulingHouse.SIXTH,"TAURUS", R.string.virgo_storyTitle, R.string.virgo_storyContent, R.string.virgo_astronomicalFacts, R.string.virgo_symbolismsFact, R.string.virgo_personalityFacts),
    LIBRA(R.drawable.libra_icon, R.drawable.libra_cons,"The Scales","SEPT 23","OCT 22",  R.drawable.sketch_libra, R.drawable.symbol_libra, Element.AIR, Quality.CARDINAL, RulingPlanet.VENUS_LIBRA, RulingHouse.SEVENTH, "AQUARIUS", R.string.libra_storyTitle, R.string.libra_storyContent, R.string.libra_astronomicalFacts, R.string.libra_symbolismsFact, R.string.libra_personalityFacts),
    SCORPIO(R.drawable.scorpio_icon, R.drawable.scorpio_cons,"The Scorpion","OCT 23", "NOV 21",  R.drawable.sketch_scorpio, R.drawable.symbol_scorpio, Element.WATER, Quality.FIXED, RulingPlanet.PLUTO, RulingHouse.EIGHTH, "PISCES", R.string.scorpio_storyTitle, R.string.scorpio_storyContent, R.string.scorpio_astronomicalFacts, R.string.scorpio_symbolismsFact, R.string.scorpio_personalityFacts),
    SAGITTARIUS(R.drawable.sagittarius_icon, R.drawable.sagittarius_cons,"The Archer","NOV 22", "DEC 21",  R.drawable.sketch_sagittarius, R.drawable.symbol_sagittarius, Element.FIRE, Quality.MUTABLE, RulingPlanet.JUPITER, RulingHouse.NINTH, "ARIES", R.string.sagittarius_storyTitle, R.string.sagittarius_storyContent, R.string.sagittarius_astronomicalFacts, R.string.sagittarius_symbolismsFact, R.string.sagittarius_personalityFacts),
    CAPRICORN(R.drawable.capricorn_icon, R.drawable.capricorn_cons,"The Goat","DEC 22", "JAN 19",  R.drawable.sketch_capricorn, R.drawable.symbol_capricorn, Element.EARTH, Quality.CARDINAL, RulingPlanet.SATURN, RulingHouse.TENTH, "TAURUS", R.string.capricorn_storyTitle, R.string.capricorn_storyContent, R.string.capricorn_astronomicalFacts, R.string.capricorn_symbolismsFact, R.string.capricorn_personalityFacts),
    AQUARIUS(R.drawable.aquarius_icon, R.drawable.aquarius_cons,"The Water Bearer","JAN 20", "FEB 18",  R.drawable.sketch_aquarius, R.drawable.symbol_aquarius, Element.AIR, Quality.FIXED, RulingPlanet.URANUS, RulingHouse.ELEVENTH, "GEMINI", R.string.aquarius_storyTitle, R.string.aquarius_storyContent, R.string.aquarius_astronomicalFacts, R.string.aquarius_symbolismsFact, R.string.aquarius_personalityFacts),
    PISCES(R.drawable.pisces_icon, R.drawable.pisces_cons,"The Fish","FEB 19", "MAR 20",  R.drawable.sketch_pisces, R.drawable.symbol_pisces, Element.WATER, Quality.MUTABLE, RulingPlanet.NEPTUNE, RulingHouse.TWELFTH, "CANCER", R.string.pisces_storyTitle, R.string.pisces_storyContent, R.string.pisces_astronomicalFacts, R.string.pisces_symbolismsFact, R.string.pisces_personalityFacts);

    fun concatStartEndDatesCapsCase(): String {
        return "$startDate - $endDate"
    }

    fun concatStartEndDatesCamelCase(): String {
        return "${startDate.lowercase().replaceFirstChar { it.uppercase() }} - ${endDate.lowercase().replaceFirstChar { it.uppercase() }}"
    }


    companion object {

        fun getAllZodiacSigns(): List<ZodiacSign> {
            return entries
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun parseDate(date: LocalDate): ZodiacSign {

            val month = date.monthValue
            val day = date.dayOfMonth

            return when {
                month == 3 && day >= 21 || month == 4 && day <= 19 -> ARIES
                month == 4 && day >= 20 || month == 5 && day <= 20 -> TAURUS
                month == 5 && day >= 21 || month == 6 && day <= 20 -> GEMINI
                month == 6 && day >= 21 || month == 7 && day <= 22 -> CANCER
                month == 7 && day >= 23 || month == 8 && day <= 22 -> LEO
                month == 8 && day >= 23 || month == 9 && day <= 22 -> VIRGO
                month == 9 && day >= 23 || month == 10 && day <= 22 -> LIBRA
                month == 10 && day >= 23 || month == 11 && day <= 21 -> SCORPIO
                month == 11 && day >= 22 || month == 12 && day <= 21 -> SAGITTARIUS
                month == 12 && day >= 22 || month == 1 && day <= 19 -> CAPRICORN
                month == 1 && day >= 20 || month == 2 && day <= 18 -> AQUARIUS
                month == 2 && day >= 19 || month == 3 && day <= 20 -> PISCES
                else -> ARIES
            }
        }
    }
}