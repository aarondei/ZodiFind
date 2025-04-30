package cit.edu.zodifind.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import cit.edu.zodifind.R
import cit.edu.zodifind.helpers.ZodiacSign

class DetailsFragment : Fragment() {
    private lateinit var zodiacSign: ZodiacSign

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val zodiacSignName = it.getString(ARG_ZODIAC_SIGN) ?: ""
            try {
                zodiacSign = ZodiacSign.valueOf(zodiacSignName)
            } catch (e: IllegalArgumentException) {
                zodiacSign = ZodiacSign.AQUARIUS
            }
        }

        // Setup views to display zodiac details
        val detailsDateRange = view.findViewById<TextView>(R.id.detailsDateRange)
        val detailsSymbol = view.findViewById<TextView>(R.id.detailsSymbol)
        val detailsElement = view.findViewById<TextView>(R.id.detailsElement)
        val detailsQuality = view.findViewById<TextView>(R.id.detailsQuality)
        val detailsRulingPlanet = view.findViewById<TextView>(R.id.detailsRulingPlanet)

        detailsDateRange.text = zodiacSign.concatStartEndDates()
        detailsSymbol.text = "Symbol • ${getZodiacSymbol(zodiacSign)}"
        detailsElement.text = "Element • ${getZodiacElement(zodiacSign)}"
        detailsQuality.text = "Quality • ${getZodiacQuality(zodiacSign)}"
        detailsRulingPlanet.text = "Ruling Planet • ${getZodiacRulingPlanet(zodiacSign)}"
    }

    // These methods would return the appropriate attributes for each sign
    private fun getZodiacSymbol(sign: ZodiacSign): String {
        return when (sign) {
            ZodiacSign.ARIES -> "The Ram"
            ZodiacSign.TAURUS -> "The Bull"
            ZodiacSign.GEMINI -> "The Twins"
            ZodiacSign.CANCER -> "The Crab"
            ZodiacSign.LEO -> "The Lion"
            ZodiacSign.VIRGO -> "The Maiden"
            ZodiacSign.LIBRA -> "The Scales"
            ZodiacSign.SCORPIO -> "The Scorpion"
            ZodiacSign.SAGITTARIUS-> "The Archer"
            ZodiacSign.CAPRICORN -> "The Goat"
            ZodiacSign.AQUARIUS -> "The Water Bearer"
            ZodiacSign.PISCES -> "The Fish"
            else -> ""
        }
    }

    private fun getZodiacElement(sign: ZodiacSign): String {
        return when (sign) {
            ZodiacSign.ARIES, ZodiacSign.LEO, ZodiacSign.SAGITTARIUS -> "Fire"
            ZodiacSign.TAURUS, ZodiacSign.VIRGO, ZodiacSign.CAPRICORN -> "Earth"
            ZodiacSign.GEMINI, ZodiacSign.LIBRA, ZodiacSign.AQUARIUS -> "Air"
            ZodiacSign.CANCER, ZodiacSign.SCORPIO, ZodiacSign.PISCES -> "Water"
            else -> ""
        }
    }

    private fun getZodiacQuality(sign: ZodiacSign): String {
        return when (sign) {
            ZodiacSign.ARIES, ZodiacSign.CANCER, ZodiacSign.LIBRA, ZodiacSign.CAPRICORN -> "Cardinal"
            ZodiacSign.TAURUS, ZodiacSign.LEO, ZodiacSign.SCORPIO, ZodiacSign.AQUARIUS -> "Fixed"
            ZodiacSign.GEMINI, ZodiacSign.VIRGO, ZodiacSign.SAGITTARIUS, ZodiacSign.PISCES -> "Mutable"
            else -> ""
        }
    }

    private fun getZodiacRulingPlanet(sign: ZodiacSign): String {
        return when (sign) {
            ZodiacSign.ARIES -> "Mars"
            ZodiacSign.TAURUS -> "Venus"
            ZodiacSign.GEMINI -> "Mercury"
            ZodiacSign.CANCER -> "Moon"
            ZodiacSign.LEO -> "Sun"
            ZodiacSign.VIRGO -> "Mercury"
            ZodiacSign.LIBRA -> "Venus"
            ZodiacSign.SCORPIO -> "Pluto"
            ZodiacSign.SAGITTARIUS -> "Jupiter"
            ZodiacSign.CAPRICORN -> "Saturn"
            ZodiacSign.AQUARIUS -> "Uranus"
            ZodiacSign.PISCES -> "Neptune"
            else -> ""
        }
    }

    companion object {
        private const val ARG_ZODIAC_SIGN = "zodiac_sign"

        fun newInstance(zodiacSign: String): DetailsFragment {
            val fragment = DetailsFragment()
            val args = Bundle()
            args.putString(ARG_ZODIAC_SIGN, zodiacSign)
            fragment.arguments = args
            return fragment
        }
    }
}
