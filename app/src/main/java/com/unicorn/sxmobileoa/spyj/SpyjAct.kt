package com.unicorn.sxmobileoa.spyj

import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.ui.BaseAct
import kotlinx.android.synthetic.main.act_spyj.*

class SpyjAct : BaseAct() {

    override val layoutId = R.layout.act_spyj



    override fun initViews() {
        viewPager.offscreenPageLimit = Global.detail.flowNodeList.size - 1
        viewPager.adapter = SpyjPagerAdapter( supportFragmentManager)
        val currentItem = intent.getIntExtra(Key.position, 0)
        viewPager.currentItem = currentItem
    }

    override fun bindIntent() {
    }

}
