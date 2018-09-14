package com.unicorn.sxmobileoa.simple.code.ui

import android.support.v7.widget.LinearLayoutManager
import com.orhanobut.logger.Logger
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.addDefaultItemDecoration
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.simple.code.network.GetCode
import dart.DartModel
import kotlinx.android.synthetic.main.act_title_recycler.*

class CodeAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle(model.title)
        initRecyclerView()
    }

    private lateinit var codeAdapter: CodeAdapter

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            codeAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration()
        }
    }

    override fun bindIntent() {
        GetCode(model.code).toMaybe(this)
                .subscribe {
                    Logger.e(it.toString())
                    //                    codeAdapter.setNewData(it)
                }
    }

    override val layoutId = R.layout.act_title_recycler

    @DartModel
    lateinit var model: CodeActNavigationModel

}
