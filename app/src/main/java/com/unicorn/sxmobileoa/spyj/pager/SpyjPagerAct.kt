package com.unicorn.sxmobileoa.spyj.pager

import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.ui.BaseAct
import kotlinx.android.synthetic.main.act_spyj_pager.*

class SpyjPagerAct : BaseAct() {

    override val layoutId = R.layout.act_spyj_pager

    override fun initViews() {
        viewPager.apply {
            offscreenPageLimit = Global.detail.flowNodeList.size - 1
            adapter = SpyjPagerAdapter(supportFragmentManager)
            currentItem = intent.getIntExtra(Key.position, 0)
        }
    }

    override fun bindIntent() {
    }

}
