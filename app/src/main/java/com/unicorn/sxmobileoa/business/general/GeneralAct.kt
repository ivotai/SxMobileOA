package com.unicorn.sxmobileoa.business.general

import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.clicks
import kotlinx.android.synthetic.main.act_general.*
import me.yokeyword.fragmentation.SupportActivity

abstract class GeneralAct : SupportActivity() {

    abstract val title: String

    abstract val adapter: GeneralPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_general)

        tvTitle.text = title
        viewPager.adapter = adapter

        tabYiBan.clicks().map { 0 }
                .mergeWith(tabDaiBan.clicks().map { 1 })
                .subscribe {
                    val indicator = if (it == 0) indicatorYiBan else indicatorDaiBan
                    val indicatorUnSelect = if (it == 1) indicatorYiBan else indicatorDaiBan
                    indicator.setBackgroundColor(ContextCompat.getColor(this@GeneralAct, R.color.colorPrimary))
                    indicatorUnSelect.setBackgroundColor(Color.TRANSPARENT)
                    viewPager.setCurrentItem(it, false)
                }
    }

}
