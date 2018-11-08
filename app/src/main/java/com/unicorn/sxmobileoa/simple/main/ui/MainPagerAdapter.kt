package com.unicorn.sxmobileoa.simple.main.ui

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.unicorn.sxmobileoa.n.ggxx.ui.GgxxPagerFra

class MainPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return if (position == 0) HomeFra() else GgxxPagerFra()
    }

    override fun getCount() = 4

    private val pageTitles = listOf("首页", "公告", "新闻", "我的")

    override fun getPageTitle(position: Int) = pageTitles[position]

}