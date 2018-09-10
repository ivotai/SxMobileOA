package com.unicorn.sxmobileoa.login.court.ui

import android.support.v7.widget.LinearLayoutManager
import android.widget.TextView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.login.court.model.Court
import com.unicorn.sxmobileoa.login.court.network.CourtUseCase
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import kotlinx.android.synthetic.main.act_court.*

class CourtAct : BaseAct() {

    override val layoutId = R.layout.act_court

    private val courtAdapter = CourtAdapter()

    private lateinit var etKeyword: TextView

    override fun initViews() {
        titleBar.setTitle("选择法院")
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            courtAdapter.bindToRecyclerView(this)
            HorizontalDividerItemDecoration.Builder(context)
                    .colorResId(R.color.md_grey_300)
                    .size(1)
                    .build().let { this@apply.addItemDecoration(it) }
        }

        val headerView = CourtHeaderView(this)
        etKeyword = headerView.etKeyword
        courtAdapter.addHeaderView(headerView)
    }

    override fun bindIntent() {
        CourtUseCase()
                .toMaybe(this)
                .doOnSuccess { prepareKeyword(it) }
                .subscribe { t -> courtAdapter.setNewData(t) }

        // TODO DELETE FAKER METHOD
//        Faker().getCourtMaybe()
//                .flatMapObservable { Observable.fromIterable(it) }
//
//                .toSortedList(object : Comparator<Court> {
//                    override fun compare(o1: Court, o2: Court): Int {
//                        o1.dm > o2.dm
//                    }
//                }).subscribe { t -> courtAdapter.setNewData(t) }
    }

    private fun prepareKeyword(courtList: List<Court>) {
        RxTextView.textChanges(etKeyword).map { it.toString() }.subscribe { keyword ->
            courtList.filter { it.dmms.contains(keyword) }
                    .let { courtAdapter.setNewData(it) }
        }
    }

}
