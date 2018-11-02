package com.unicorn.sxmobileoa.n.add

import android.annotation.SuppressLint
import android.view.View
import com.orhanobut.logger.Logger
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.n.add.network.AddQjsq
import kotlinx.android.synthetic.main.act_qjsq_add.*
import kotlinx.android.synthetic.main.header_view_qjsq.*

class QjsqAddAct : BaseAct() {

    override val layoutId = R.layout.act_qjsq_add

    override fun initViews() {
        titleBar.setTitle("请假申请")
        divider.visibility = View.GONE
    }

    @SuppressLint("CheckResult")
    override fun bindIntent() {
        AddQjsq().toMaybe(this).subscribe {
            Logger.e(it.toString())
        }
    }

}
