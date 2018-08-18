package com.unicorn.sxmobileoa.business.general

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

abstract class GeneralPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    final override fun getCount() = 2

}