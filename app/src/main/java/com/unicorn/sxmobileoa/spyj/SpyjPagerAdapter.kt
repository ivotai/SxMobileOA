package com.unicorn.sxmobileoa.spyj

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.detail.model.Detail

class SpyjPagerAdapter(val detail: Detail, fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getCount() = detail.flowNodeList.size

    override fun getItem(position: Int): Fragment {
        return SpyjFra().apply {
            val bundle = Bundle()
            bundle.putSerializable(Key.flowNode, detail.flowNodeList[position])
            arguments = bundle
        }
    }

}