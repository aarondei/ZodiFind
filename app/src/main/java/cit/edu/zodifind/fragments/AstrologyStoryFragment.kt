package cit.edu.zodifind.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import cit.edu.zodifind.R
import cit.edu.zodifind.data.astrology.ZodiacSign

class AstrologyStoryFragment : Fragment() {
    private lateinit var zodiacSign: ZodiacSign

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_story, container, false)
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

        val storyTitle = view.findViewById<TextView>(R.id.storyTitle)
        val storyContent = view.findViewById<TextView>(R.id.storyContent)

        storyTitle.text = getString(zodiacSign.storyTitle)
        storyContent.text = getString(zodiacSign.storyContent)
    }

    companion object {
        private const val ARG_ZODIAC_SIGN = "zodiac_sign"

        fun newInstance(zodiacSign: String): AstrologyStoryFragment {
            val fragment = AstrologyStoryFragment()
            val args = Bundle()
            args.putString(ARG_ZODIAC_SIGN, zodiacSign)
            fragment.arguments = args
            return fragment
        }
    }
}