package cit.edu.zodifind.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import cit.edu.zodifind.fragments.AstrologyStoryFragment
import cit.edu.zodifind.fragments.AstrologyDetailsFragment
import cit.edu.zodifind.fragments.AstrologyFactsFragment
import cit.edu.zodifind.data.astrology.ZodiacSign

class ViewPagerAdapter(fragment: Fragment, private val zodiacSign: ZodiacSign) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AstrologyStoryFragment.newInstance(zodiacSign.name)
            1 -> AstrologyDetailsFragment.newInstance(zodiacSign.name)
            2 -> AstrologyFactsFragment.newInstance(zodiacSign.name)
            else -> AstrologyStoryFragment.newInstance(zodiacSign.name)
        }
    }
}