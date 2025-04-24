package cit.edu.zodifind.helpers

import android.os.Build
import android.widget.ImageView
import androidx.annotation.RequiresApi
import cit.edu.zodifind.R
import java.time.LocalDate
import java.util.Date

@Suppress("DEPRECATION")
enum class ZodiacSign(val symbolIcon: Int, val starIcon: Int, val representation: String, val startDate: String, val endDate: String) {
    ARIES(R.drawable.aries_icon, R.drawable.aries_cons, "The Ram","MAR 21", "APR 19"),
    TAURUS(R.drawable.taurus_icon, R.drawable.taurus_cons,"The Bull","APR 20", "MAY 20"),
    GEMINI(R.drawable.gemini_icon, R.drawable.gemini_cons,"The Twins","MAY 21", "JUN 20"),
    CANCER(R.drawable.cancer_icon, R.drawable.cancer_cons,"The Crab","JUN 21", "JUL 22"),
    LEO(R.drawable.leo_icon, R.drawable.leo_cons,"The Lion","JUL 23", "AUG 22"),
    VIRGO(R.drawable.virgo_icon, R.drawable.virgo_cons,"The Maiden","AUG 23", "SEPT 22"),
    LIBRA(R.drawable.libra_icon, R.drawable.leo_cons,"The Scales","SEPT 23","OCT 22"),
    SCORPIO(R.drawable.scorpio_icon, R.drawable.scorpio_cons,"The Scorpion","OCT 23", "NOV 21"),
    SAGITTARIUS(R.drawable.sagittarius_icon, R.drawable.sagittarius_cons,"The Archer","NOV 22", "DEC 21"),
    CAPRICORN(R.drawable.capricorn_icon, R.drawable.capricorn_cons,"The Goat","DEC 22", "JAN 19"),
    AQUARIUS(R.drawable.aquarius_icon, R.drawable.aquarius_cons,"The Water Bearer","JAN 20", "FEB 18"),
    PISCES(R.drawable.pisces_icon, R.drawable.pisces_cons,"The Fish","FEB 19", "MAR 20");

    fun concatStartEndDates(): String {
        return "$startDate - $endDate"
    }

    companion object { // makes this method static

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