package cit.edu.zodifind.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import cit.edu.zodifind.R
import cit.edu.zodifind.data.astrology.ZodiacSign

class AstrologyDetailsFragment : Fragment() {
    private lateinit var zodiacSign: ZodiacSign

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    @SuppressLint("SetTextI18n")
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
        val detailsRulingHouse = view.findViewById<TextView>(R.id.detailsRulingHouse)
        val detailsCompatibility = view.findViewById<TextView>(R.id.detailsCompatibility)

        // setup images to display
        val imgSymbol = view.findViewById<ImageView>(R.id.imgSymbol)
        val imgElement = view.findViewById<ImageView>(R.id.imgElement)
        val imgQuality = view.findViewById<ImageView>(R.id.imgQuality)
        val imgRulingPlanet = view.findViewById<ImageView>(R.id.imgRulingPlanet)
        val imgRulingHouse = view.findViewById<ImageView>(R.id.imgRulingHouse)

        // setup details
        detailsDateRange.text = zodiacSign.concatStartEndDatesCamelCase()
        detailsSymbol.text = "Symbol • ${zodiacSign.representation}"
        detailsElement.text = "Element • ${zodiacSign.element.name.lowercase().replaceFirstChar { it.uppercase() }}"
        detailsQuality.text = "Quality • ${zodiacSign.quality.name.lowercase().replaceFirstChar { it.uppercase() }}"
        detailsRulingPlanet.text = "Ruling Planet • ${zodiacSign.rulingPlanet.title.lowercase().replaceFirstChar { it.uppercase() }}"
        detailsRulingHouse.text = "Ruling House • ${zodiacSign.rulingHouse.name.lowercase().replaceFirstChar { it.uppercase() }}"
        detailsCompatibility.text = "Compatible • ${zodiacSign.compatibility.lowercase().replaceFirstChar { it.uppercase() }}"


        // set up images
        imgSymbol.setImageResource(zodiacSign.symbolIcon)
        imgElement.setImageResource(zodiacSign.element.symbolIcon)
        imgQuality.setImageResource(zodiacSign.quality.symbolIcon)
        imgRulingPlanet.setImageResource(zodiacSign.rulingPlanet.symbolIcon)
        imgRulingHouse.setImageResource(zodiacSign.rulingHouse.symbolIcon)


        // element
        val tabElement = view.findViewById<LinearLayout>(R.id.tabElement)
        val tabElementToggle = view.findViewById<LinearLayout>(R.id.tabElementToggle)
        val contentElement = view.findViewById<TextView>(R.id.contentElement)

        tabElement.setOnClickListener {
            if (tabElementToggle.visibility == View.VISIBLE) {
                tabElementToggle.visibility = View.GONE
                tabElement.setBackgroundResource(R.drawable.rounded_border)
            } else {
                tabElementToggle.visibility = View.VISIBLE
                tabElement.setBackgroundResource(R.drawable.rounded_border_selected)
                contentElement.text = getString(zodiacSign.element.description)
            }
        }

        // quality
        val tabQuality = view.findViewById<LinearLayout>(R.id.tabQuality)
        val tabQualityToggle = view.findViewById<LinearLayout>(R.id.tabQualityToggle)
        val contentQuality = view.findViewById<TextView>(R.id.contentQuality)

        tabQuality.setOnClickListener {
            if (tabQualityToggle.visibility == View.VISIBLE) {
                tabQualityToggle.visibility = View.GONE
                tabQuality.setBackgroundResource(R.drawable.rounded_border)
            } else {
                tabQualityToggle.visibility = View.VISIBLE
                tabQuality.setBackgroundResource(R.drawable.rounded_border_selected)
                contentQuality.text = getString(zodiacSign.quality.description)
            }
        }

        // ruling house
        val tabRulingHouse = view.findViewById<LinearLayout>(R.id.tabRulingHouse)
        val tabRulingHouseToggle = view.findViewById<LinearLayout>(R.id.tabRulingHouseToggle)
        val contentRulingHouse = view.findViewById<TextView>(R.id.contentRulingHouse)

        tabRulingHouse.setOnClickListener {
            if (tabRulingHouseToggle.visibility == View.VISIBLE) {
                tabRulingHouseToggle.visibility = View.GONE
                tabRulingHouse.setBackgroundResource(R.drawable.rounded_border)
            } else {
                tabRulingHouseToggle.visibility = View.VISIBLE
                tabRulingHouse.setBackgroundResource(R.drawable.rounded_border_selected)
                contentRulingHouse.text = getString(zodiacSign.rulingHouse.description)
            }
        }

        // ruling planet
        val tabRulingPlanet = view.findViewById<LinearLayout>(R.id.tabRulingPlanet)
        val tabRulingPlanetToggle = view.findViewById<LinearLayout>(R.id.tabRulingPlanetToggle)
        val contentRulingPlanet = view.findViewById<TextView>(R.id.contentRulingPlanet)

        tabRulingPlanet.setOnClickListener {
            if (tabRulingPlanetToggle.visibility == View.VISIBLE) {
                tabRulingPlanetToggle.visibility = View.GONE
                tabRulingPlanet.setBackgroundResource(R.drawable.rounded_border)
            } else {
                tabRulingPlanetToggle.visibility = View.VISIBLE
                tabRulingPlanet.setBackgroundResource(R.drawable.rounded_border_selected)
                contentRulingPlanet.text = getString(zodiacSign.rulingPlanet.description)
            }
        }
    }

    companion object {
        private const val ARG_ZODIAC_SIGN = "zodiac_sign"

        fun newInstance(zodiacSign: String): AstrologyDetailsFragment {
            val fragment = AstrologyDetailsFragment()
            val args = Bundle()
            args.putString(ARG_ZODIAC_SIGN, zodiacSign)
            fragment.arguments = args
            return fragment
        }
    }
}
