package com.unicorn.sxmobileoa.detail.ui

import com.orhanobut.logger.Logger
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.main.dbxx.model.Param
import com.unicorn.sxmobileoa.detail.network.DetailUseCase

class DetailAct : BaseAct() {

    override val layoutId = R.layout.act_detail


    override fun initViews() {
    }

    override fun bindIntent() {
        val moduleCode = intent.getStringExtra(Key.moduleCode)
        val param = intent.getSerializableExtra(Key.param) as Param
        DetailUseCase(moduleCode, param).toMaybe(this).subscribe {
            Logger.e(it.toString())
        }
    }

}
