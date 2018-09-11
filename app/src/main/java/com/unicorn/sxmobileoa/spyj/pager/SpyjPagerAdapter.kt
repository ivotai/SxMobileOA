package com.unicorn.sxmobileoa.spyj.pager

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.spyj.spyj.SpyjFra

class SpyjPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getCount() = Global.spd.flowNodeList.size

    override fun getItem(position: Int) = SpyjFra().apply {
        arguments = Bundle().apply {
            putInt(Key.position, position)
        }
    }

}