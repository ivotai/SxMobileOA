package com.unicorn.sxmobileoa.select.dept.ui

import android.support.v7.widget.LinearLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.addDefaultItemDecoration
import com.unicorn.sxmobileoa.app.mess.KeywordHeaderView
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.mess.model.SelectResult
import com.unicorn.sxmobileoa.app.mess.model.SelectWrapper
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.textChanges
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.select.dept.model.Dept
import com.unicorn.sxmobileoa.select.dept.network.GetDept
import dart.DartModel
import kotlinx.android.synthetic.main.act_title_recycler.*

class DeptAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("选择部门")
        initRecyclerView()
    }

    private val mAdapter = DeptAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@DeptAct)
            mAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration()
        }
    }

    override fun bindIntent() {
        getDept()
        clickOperation()
    }

    private lateinit var headerView: KeywordHeaderView

    private fun addKeywordHeaderView() {
        headerView = KeywordHeaderView(this).apply {
            setHint("请输入部门")
            mAdapter.addHeaderView(this)
        }
    }

    private fun getDept() {
        GetDept().toMaybe(this)
                .map { it.deptData }
                .doOnSuccess {
                    list -> list.forEach { if (it.isFather) it.text = "${it.text} 父节点" }
                }
                .doOnSuccess {
                    addKeywordHeaderView()
                    textChangeKeyword(it)
                }
                .map { it.map { dept -> SelectWrapper(dept) } }
                .subscribe { mAdapter.setNewData(it) }
    }

    private fun textChangeKeyword(allDept: List<Dept>) {
        headerView.etKeyword.textChanges()
                .subscribe { keyword ->
                    allDept.filter { dept -> dept.text.contains(keyword) }
                            .map { dept -> SelectWrapper(dept) }
                            .let { mAdapter.setNewData(it) }
                }
    }

    private fun clickOperation() {
        titleBar.setOperation("确认").safeClicks().subscribe { _ ->
            mAdapter.data
                    .filter { it.isSelected }
                    .map { it.t }
                    .let { listSelected ->
                        val result = listSelected.joinToString(",") { it.text }
                        RxBus.get().post(SelectResult(model.key, result))
                    }
            finish()
        }
    }

    @DartModel
    lateinit var model: DeptActNavigationModel

    override val layoutId = R.layout.act_title_recycler

}