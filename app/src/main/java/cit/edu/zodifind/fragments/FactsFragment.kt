package cit.edu.zodifind.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import cit.edu.zodifind.R
import cit.edu.zodifind.data.astrology.ZodiacSign

class FactsFragment : Fragment() {
    private lateinit var zodiacSign: ZodiacSign

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_facts, container, false)
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

        val factsTitle = view.findViewById<TextView>(R.id.factsTitle)
        val factsList = view.findViewById<TextView>(R.id.factsList)

        factsTitle.text = "Astronomical Facts"
        factsList.text = getZodiacFacts(zodiacSign)
    }

    private fun getZodiacFacts(sign: ZodiacSign): String {
        // Return facts for the specific zodiac sign
        return when (sign) {
            ZodiacSign.VIRGO -> "• It is the 2nd largest constellation\n" +
                    "• There are over 1200 galaxies in the Virgo Cluster\n" +
                    "• Virgo Supercluster is where our galaxy belongs"
            // Add facts for all signs
            else -> "Facts coming soon..."
        }
    }

    companion object {
        private const val ARG_ZODIAC_SIGN = "zodiac_sign"

        fun newInstance(zodiacSign: String): FactsFragment {
            val fragment = FactsFragment()
            val args = Bundle()
            args.putString(ARG_ZODIAC_SIGN, zodiacSign)
            fragment.arguments = args
            return fragment
        }
    }
}