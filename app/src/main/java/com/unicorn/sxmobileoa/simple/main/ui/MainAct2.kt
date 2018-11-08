package com.unicorn.sxmobileoa.simple.main.ui

import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.ui.BaseAct
import kotlinx.android.synthetic.main.act_main2.*
import me.majiajie.pagerbottomtabstrip.item.NormalItemView

class MainAct2 : BaseAct() {

    override val layoutId = R.layout.act_main2

    override fun initViews() {
        initViewPager()
    }

    private fun initViewPager() {
        val navigationController = tab.custom()
                .addItem(NormalItemView(this).apply { initialize(R.mipmap.home, R.mipmap.home_select, "首页") })
                .addItem(NormalItemView(this).apply { initialize(R.mipmap.notice, R.mipmap.notice_select, "公告") })
                .addItem(NormalItemView(this).apply { initialize(R.mipmap.news, R.mipmap.news_select, "新闻") })
                .addItem(NormalItemView(this).apply { initialize(R.mipmap.me, R.mipmap.me_select, "我的") })
                .build()
        navigationController.setupWithViewPager(viewPager)
        viewPager.offscreenPageLimit = 3
        viewPager.adapter = MainPagerAdapter(supportFragmentManager)
    }

    override fun bindIntent() {
    }

}
