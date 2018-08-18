package com.unicorn.sxmobileoa.general

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.unicorn.sxmobileoa.shouWen.ShouWenFra

class GeneralPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return if (position == 0) ShouWenFra() else ShouWenFra()
    }

    override fun getCount() = 2

}