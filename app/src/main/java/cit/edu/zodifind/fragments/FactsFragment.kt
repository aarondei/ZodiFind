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

        val factsAstronomicalList = view.findViewById<TextView>(R.id.factsAstronomicalList)
        val factsSymbolismsList = view.findViewById<TextView>(R.id.factsSymbolismsList)
        val factsPersonalityList = view.findViewById<TextView>(R.id.factsPersonalityList)

        factsAstronomicalList.text = getString(zodiacSign.astronomicalFacts)
        factsSymbolismsList.text = getString(zodiacSign.symbolismsFacts)
        factsPersonalityList.text = getString(zodiacSign.personalityFacts)
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