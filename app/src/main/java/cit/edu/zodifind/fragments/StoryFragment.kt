package cit.edu.zodifind.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import cit.edu.zodifind.R
import cit.edu.zodifind.helpers.ZodiacSign

class StoryFragment : Fragment() {
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

        storyTitle.text = "The Story of ${zodiacSign.name}"
        storyContent.text = getZodiacStory(zodiacSign)
    }

    private fun getZodiacStory(sign: ZodiacSign): String {
        // You would implement this to return the appropriate story for each sign
        return when (sign) {
            ZodiacSign.AQUARIUS -> "The story of Aquarius begins with..."
            ZodiacSign.PISCES -> "In ancient mythology, Pisces represents..."
            // Add stories for all signs
            else -> "Story coming soon..."
        }
    }

    companion object {
        private const val ARG_ZODIAC_SIGN = "zodiac_sign"

        fun newInstance(zodiacSign: String): StoryFragment {
            val fragment = StoryFragment()
            val args = Bundle()
            args.putString(ARG_ZODIAC_SIGN, zodiacSign)
            fragment.arguments = args
            return fragment
        }
    }
}