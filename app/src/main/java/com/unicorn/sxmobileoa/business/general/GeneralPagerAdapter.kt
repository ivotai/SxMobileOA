package com.unicorn.sxmobileoa.business.general

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

abstract class GeneralPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    abstract fun getListFra(): Fragment

    final override fun getCount() = 2

    final override fun getItem(position: Int) = getListFra().apply {
        arguments = Bundle().apply {
            val type = if (position==0)"2" else "1"
            putString("type", type)
        }
    }

}