package com.unicorn.sxmobileoa.n.ggxx.ui

import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.ui.BaseAct
import kotlinx.android.synthetic.main.act_ggxx.*

class GgxxAct : BaseAct() {

    override val layoutId = R.layout.act_ggxx

    override fun initViews() {
        titleBar.setTitle("消息公告")
        viewPager.adapter = GgxxPagerAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun bindIntent() {
    }

}
