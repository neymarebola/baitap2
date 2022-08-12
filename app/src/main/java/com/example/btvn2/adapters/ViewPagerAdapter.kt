package com.example.btvn2.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.btvn2.fragments.BongdaFragment
import com.example.btvn2.fragments.ChobanFragment
import com.example.btvn2.fragments.HomeFragment
import com.example.btvn2.fragments.TheodoiFragment

class ViewPagerAdapter(fm: FragmentManager, behavior: Int) :
    FragmentStatePagerAdapter(fm, behavior) {
    override fun getCount(): Int {
        return 3;
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return ChobanFragment()
            1 -> return TheodoiFragment()
            2 -> return BongdaFragment()
        }
        return ChobanFragment()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 ->return "Cho ban"
            1 -> return "Theo doi"
            2 -> return "Bong da"
        }
        return "Cho ban"
    }
}