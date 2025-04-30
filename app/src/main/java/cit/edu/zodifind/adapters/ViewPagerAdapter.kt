package cit.edu.zodifind.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import cit.edu.zodifind.fragments.StoryFragment
import cit.edu.zodifind.fragments.DetailsFragment
import cit.edu.zodifind.fragments.FactsFragment
import cit.edu.zodifind.helpers.ZodiacSign

class ViewPagerAdapter(fragment: Fragment, private val zodiacSign: ZodiacSign) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> StoryFragment.newInstance(zodiacSign.name)
            1 -> DetailsFragment.newInstance(zodiacSign.name)
            2 -> FactsFragment.newInstance(zodiacSign.name)
            else -> StoryFragment.newInstance(zodiacSign.name)
        }
    }
}