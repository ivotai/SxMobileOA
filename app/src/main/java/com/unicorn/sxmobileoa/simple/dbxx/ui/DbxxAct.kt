package com.unicorn.sxmobileoa.simple.dbxx.ui

import android.annotation.SuppressLint
import android.content.Intent
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.header.qjsq.QjsqAct
import com.unicorn.sxmobileoa.header.ycsq.YcsqAct
import dart.DartModel
import kotlinx.android.synthetic.main.act_title_tab_viewpager.*

class DbxxAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("${model.menu.text}列表")
        viewPager.adapter = DbxxPagerAdapter(supportFragmentManager, model.menu)
        tabLayout.setupWithViewPager(viewPager)
    }

    @SuppressLint("CheckResult")
    override fun bindIntent() {
        if (model.menu.text in listOf("请假申请", "用车申请")) {
            val cls = if (model.menu.text == "请假申请") QjsqAct::class.java else YcsqAct::class.java
            titleBar.setOperation("新建").safeClicks().subscribe { _ ->
                startActivity(Intent(this, cls).apply {
                    putExtra(Key.menu, model.menu)
                    putExtra(Key.isCreate, true)
                    putExtra(Key.type, "1")
                })
            }
        }
    }

    // ===================== ignore =====================

    @DartModel
    lateinit var model: DbxxActNavigationModel

    override val layoutId: Int = R.layout.act_title_tab_viewpager

}
