package com.unicorn.sxmobileoa.bgsx

import android.content.Intent
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.base.BaseAct
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.dblb.DblbAct
import kotlinx.android.synthetic.main.act_bgsx.*

class BgsxAct : BaseAct() {

    override val layoutId = R.layout.act_bgsx

    override fun initViews() {
        titleBar.setTitle("办公事项")
    }

    override fun bindIntent() {
        jddb.safeClicks().subscribe { startActivity(Intent(this, DblbAct::class.java)) }
    }

}
