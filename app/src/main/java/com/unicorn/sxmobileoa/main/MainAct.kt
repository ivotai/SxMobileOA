package com.unicorn.sxmobileoa.main

import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.ui.BaseAct
import kotlinx.android.synthetic.main.act_bgsx.*

class MainAct : BaseAct() {

    override val layoutId = R.layout.act_main

    override fun initViews() {
        titleBar.setTitle("陕西省高级人民法院移动办公")
    }

    override fun bindIntent() {
    }

}
