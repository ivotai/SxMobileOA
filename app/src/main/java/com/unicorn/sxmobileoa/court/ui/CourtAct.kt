package com.unicorn.sxmobileoa.court.ui

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.orhanobut.logger.Logger
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.base.BaseAct
import com.unicorn.sxmobileoa.court.CourtUseCase
import com.unicorn.sxmobileoa.login.LoginAct
import kotlinx.android.synthetic.main.act_court.*

class CourtAct : BaseAct() {

    override val layoutId = R.layout.act_court

    private val courtAdapter = CourtAdapter()

    override fun initViews() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            courtAdapter.bindToRecyclerView(this)
        }
    }

    override fun bindIntent() {
        CourtUseCase().toSingle(this)
                .subscribe({
                    Logger.e(it.toString())
                    Global.court = it.result!![0]
                    startActivity(Intent(this, LoginAct::class.java))
                }, {
                    Logger.e(it.toString())
                })
    }

}
