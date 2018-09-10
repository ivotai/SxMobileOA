package com.unicorn.sxmobileoa.spyj

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.Key

class SpyjPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getCount() = Global.detail.flowNodeList.size

    override fun getItem(position: Int): Fragment {
        return SpyjFra().apply {
            val bundle = Bundle()
            bundle.putInt(Key.position, position)
            arguments = bundle
        }
    }

}