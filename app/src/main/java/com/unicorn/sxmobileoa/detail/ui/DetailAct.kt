package com.unicorn.sxmobileoa.detail.ui

import com.orhanobut.logger.Logger
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.detail.network.DetailUseCase
import com.unicorn.sxmobileoa.main.dbxx.model.Dbxx
import kotlinx.android.synthetic.main.act_detail.*

class DetailAct : BaseAct() {

    override val layoutId = R.layout.act_detail


    override fun initViews() {
    }

    override fun bindIntent() {
        val dbxx = intent.getSerializableExtra(Key.dbxx) as Dbxx
        titleBar.setTitle(dbxx.mainItem!!.text)
        DetailUseCase(dbxx).toMaybe(this).subscribe {
            Logger.e(it.toString())
        }
    }

}
