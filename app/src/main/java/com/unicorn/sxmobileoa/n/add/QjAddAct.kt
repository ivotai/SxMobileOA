package com.unicorn.sxmobileoa.n.add

import android.annotation.SuppressLint
import com.orhanobut.logger.Logger
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.n.add.network.AddQjSpd
import kotlinx.android.synthetic.main.act_qj_add.*

class QjAddAct : BaseAct() {

    override val layoutId = R.layout.act_qj_add

    override fun initViews() {
        titleBar.setTitle("请假申请")
    }

    @SuppressLint("CheckResult")
    override fun bindIntent() {
        AddQjSpd().toMaybe(this).subscribe {
            Logger.e(it.toString())
        }
    }

}
