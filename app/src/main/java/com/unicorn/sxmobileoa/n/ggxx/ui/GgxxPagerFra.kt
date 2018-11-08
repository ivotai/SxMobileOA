package com.unicorn.sxmobileoa.n.ggxx.ui

import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.ui.BaseFra
import kotlinx.android.synthetic.main.title_tab_viewpager.*

class GgxxPagerFra : BaseFra() {

    override val layoutId = R.layout.title_tab_viewpager

    override fun initViews() {
        titleBar.setTitle("消息公告",true)
        viewPager.adapter = GgxxPagerAdapter(childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun bindIntent() {
    }

}
