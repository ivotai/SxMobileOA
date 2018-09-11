package com.unicorn.sxmobileoa.simple.court.ui

import android.support.v7.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.widget.RxTextView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.addDefaultItemDecotation
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.g.KeywordHeaderView
import com.unicorn.sxmobileoa.simple.court.model.Court
import com.unicorn.sxmobileoa.simple.court.network.GetCourt
import kotlinx.android.synthetic.main.act_titlebar_recyclerview.*

class CourtAct : BaseAct() {

    override val layoutId = R.layout.act_titlebar_recyclerview

    override fun initViews() {
        titleBar.setTitle("选择法院")
        initRecyclerView()
    }

    private val courtAdapter = CourtAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            courtAdapter.bindToRecyclerView(this)
            addDefaultItemDecotation()
        }
        initKeyHeaderView()
    }

    private lateinit var headerView: KeywordHeaderView

    private fun initKeyHeaderView() {
        headerView = KeywordHeaderView(this)
        headerView.setHint("请输入法院全称")
        courtAdapter.addHeaderView(headerView)
    }

    override fun bindIntent() {
        GetCourt().toMaybe(this)
                .doOnSuccess { observeKeyword(it) }
                .subscribe { courtAdapter.setNewData(it) }
    }

    private fun observeKeyword(courtList: List<Court>) {
        RxTextView.textChanges(headerView.etKeyword)
                .map { it.toString() }
                .subscribe { keyword ->
                    courtList.filter { court -> court.dmms.contains(keyword) }
                            .let { courtAdapter.setNewData(it) }
                }
    }

}