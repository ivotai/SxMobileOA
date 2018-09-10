package com.unicorn.sxmobileoa.spyj

import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.detail.model.Detail
import kotlinx.android.synthetic.main.act_spyj.*

class SpyjAct : BaseAct() {

    override val layoutId = R.layout.act_spyj

    lateinit var detail: Detail

    override fun initArguments() {
        detail = intent.getSerializableExtra(Key.detail) as Detail
    }

    override fun initViews() {
        viewPager.offscreenPageLimit = detail.flowNodeList.size - 1
        viewPager.adapter = SpyjPagerAdapter(detail, supportFragmentManager)
        val currentItem = intent.getIntExtra(Key.position, 0)
        viewPager.currentItem = currentItem
    }

    override fun bindIntent() {
    }

}
