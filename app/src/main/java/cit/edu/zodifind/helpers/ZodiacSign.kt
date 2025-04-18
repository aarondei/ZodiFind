package cit.edu.zodifind.helpers

import android.widget.ImageView
import cit.edu.zodifind.R
import java.util.Date

@Suppress("DEPRECATION")
enum class ZodiacSign(val icon: Int, val representation: String, val startDate: String, val endDate: String) {
    ARIES(R.drawable.aries_icon, "The Ram","MAR 21", "APR 19"),
    TAURUS(R.drawable.taurus_icon, "The Bull","APR 20", "MAY 20"),
    GEMINI(R.drawable.gemini_icon, "The Twins","MAY 21", "JUN 20"),
    CANCER(R.drawable.cancer_icon, "The Crab","JUN 21", "JUL 22"),
    LEO(R.drawable.leo_icon, "The Lion","JUL 23", "AUG 22"),
    VIRGO(R.drawable.virgo_icon, "The Maiden","AUG 23", "SEPT 22"),
    LIBRA(R.drawable.libra_icon, "The Scales","SEPT 23","OCT 22"),
    SCORPIO(R.drawable.scorpio_icon, "The Scorpion","OCT 23", "NOV 21"),
    SAGITTARIUS(R.drawable.sagittarius_icon, "The Archer","NOV 22", "DEC 21"),
    CAPRICORN(R.drawable.capricorn_icon, "The Goat","DEC 22", "JAN 19"),
    AQUARIUS(R.drawable.aquarius_icon, "The Water Bearer","JAN 20", "FEB 18"),
    PISCES(R.drawable.pisces_icon, "The Fish","FEB 19", "MAR 20");

    fun concatStartEndDates(): String {
        return "$startDate - $endDate"
    }

    fun parseDate(date: Date): ZodiacSign {

        val month = date.month +1
        val day = date.date

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