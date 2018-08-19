package com.unicorn.sxmobileoa.business.general

import android.graphics.Color
import android.support.v4.content.ContextCompat
import com.jakewharton.rxbinding2.support.v4.view.RxViewPager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.base.BaseAct
import com.unicorn.sxmobileoa.app.clicks
import kotlinx.android.synthetic.main.act_general.*

abstract class GeneralAct : BaseAct() {

    abstract val title: String

    abstract val adapter: GeneralPagerAdapter
    override val layoutId = R.layout.act_general

    override fun initViews() {
        tvTitle.text = title
        viewPager.adapter = adapter
    }

    private val POS_DAI_BAN = 0
    private val POS_YI_BAN = 1

    override fun bindIntent() {
        tabDaiBan.clicks().map { POS_DAI_BAN }
                .mergeWith(tabYiBan.clicks().map { POS_YI_BAN })
                .subscribe {
                    selectTab(it)
                    viewPager.currentItem = it
                }

        RxViewPager.pageSelections(viewPager)
                .subscribe { selectTab(it) }
    }

    private fun selectTab(pos: Int) {
        val colorSelected = ContextCompat.getColor(this@GeneralAct, R.color.colorPrimary)
        val colorUnSelected = Color.TRANSPARENT
        indicatorDaiBan.setBackgroundColor(if (pos == POS_DAI_BAN) colorSelected else colorUnSelected)
        indicatorYiBan.setBackgroundColor(if (pos == POS_YI_BAN) colorSelected else colorUnSelected)
    }

}
