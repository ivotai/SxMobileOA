package com.unicorn.sxmobileoa.main

import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.base.BaseAct
import com.unicorn.sxmobileoa.app.safeClicks
import kotlinx.android.synthetic.main.act_main.*

class MainAct : BaseAct() {

    override val layoutId = R.layout.act_main

    override fun initViews() {
        titleBar.setTitle("办公事项")
    }

    override fun bindIntent() {
        jddb.safeClicks().subscribe {  }
    }

}
