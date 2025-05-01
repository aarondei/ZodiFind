package cit.edu.zodifind.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import cit.edu.zodifind.R
import cit.edu.zodifind.adapters.ViewPagerAdapter
import cit.edu.zodifind.data.astrology.ZodiacSign

class ViewPagerFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabStory: TextView
    private lateinit var tabDetails: TextView
    private lateinit var tabFacts: TextView
    private lateinit var zodiacName: TextView
    private lateinit var zodiacTitle: TextView
    private lateinit var zodiacSign: ZodiacSign

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get zodiac sign from arguments
        arguments?.let {
            val zodiacSignName = it.getString("zodiac_sign") ?: ""
            try {
                zodiacSign = ZodiacSign.valueOf(zodiacSignName)
            } catch (e: IllegalArgumentException) {
                // Default to first zodiac if invalid
                zodiacSign = ZodiacSign.AQUARIUS
            }
        }

        // Initialize views
        viewPager = view.findViewById(R.id.zodiacViewPager)
        tabStory = view.findViewById(R.id.tab_story)
        tabDetails = view.findViewById(R.id.tab_details)
        tabFacts = view.findViewById(R.id.tab_facts)
        zodiacName = view.findViewById(R.id.tvZodiacFocusedItemName)
        zodiacTitle = view.findViewById(R.id.tvZodiacFocusedItemTitle)

        // Set zodiac info
        zodiacName.text = zodiacSign.name
        zodiacTitle.text = zodiacSign.representation

        // Setup ViewPager
        setupViewPager()

        // Setup tabs
        setupTabs()
    }

    private fun setupViewPager() {
        val pagerAdapter = ViewPagerAdapter(this, zodiacSign)
        viewPager.adapter = pagerAdapter

        // Handle page changes
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateTabSelection(position)
            }
        })
    }

    private fun setupTabs() {
        tabStory.setOnClickListener {
            viewPager.currentItem = 0
            updateTabSelection(0)
        }

        tabDetails.setOnClickListener {
            viewPager.currentItem = 1
            updateTabSelection(1)
        }

        tabFacts.setOnClickListener {
            viewPager.currentItem = 2
            updateTabSelection(2)
        }

        // Set default tab
        updateTabSelection(0)
    }

    private fun updateTabSelection(position: Int) {
        // Reset all tabs to default style
        tabStory.alpha = 0.6f
        tabDetails.alpha = 0.6f
        tabFacts.alpha = 0.6f

        // Highlight selected tab
        when (position) {
            0 -> tabStory.alpha = 1.0f
            1 -> tabDetails.alpha = 1.0f
            2 -> tabFacts.alpha = 1.0f
        }
    }
}
