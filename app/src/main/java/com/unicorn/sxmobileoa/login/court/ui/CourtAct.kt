package com.unicorn.sxmobileoa.login.court.ui

import android.support.v7.widget.LinearLayoutManager
import android.widget.TextView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.addDefaultItemDecotation
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.keyword.KeywordHeaderView
import com.unicorn.sxmobileoa.login.court.model.Court
import com.unicorn.sxmobileoa.login.court.network.GetCourt
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
        addKeyHeaderView()
    }

    private lateinit var etKeyword: TextView

    private fun addKeyHeaderView() {
        val keywordHeaderView = KeywordHeaderView(this)
        courtAdapter.addHeaderView(keywordHeaderView)
        etKeyword = keywordHeaderView.etKeyword
        keywordHeaderView.setHint("请输入法院全称")
    }

    override fun bindIntent() {
        GetCourt().toMaybe(this)
                .doOnSuccess { observeKeyword(it) }
                .subscribe { t -> courtAdapter.setNewData(t) }
    }

    private fun observeKeyword(courtList: List<Court>) {
        RxTextView.textChanges(etKeyword)
                .map { it.toString() }
                .subscribe { keyword ->
                    courtList.filter { court -> court.dmms.contains(keyword) }
                            .let { courtAdapter.setNewData(it) }
                }
    }

}
