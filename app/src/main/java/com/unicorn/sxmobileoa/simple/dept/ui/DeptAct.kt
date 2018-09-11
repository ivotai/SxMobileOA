package com.unicorn.sxmobileoa.simple.dept.ui

import android.support.v7.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.widget.RxTextView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.addDefaultItemDecoration
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.mess.KeywordHeaderView
import com.unicorn.sxmobileoa.app.mess.SelectWrapper
import com.unicorn.sxmobileoa.simple.dept.model.Dept
import com.unicorn.sxmobileoa.simple.dept.model.DeptSelectResult
import com.unicorn.sxmobileoa.simple.dept.network.GetDept
import kotlinx.android.synthetic.main.act_titlebar_recyclerview.*

class DeptAct : BaseAct() {

    private lateinit var tag: String

    override fun initArguments() {
        tag = intent.getStringExtra(Key.tag)
    }

    override val layoutId = R.layout.act_titlebar_recyclerview

    override fun initViews() {
        titleBar.setTitle("选择部门")
        initRecyclerView()
    }

    private val deptAdapter = DeptAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@DeptAct)
            deptAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration()
        }
        initKeyHeaderView()
    }

    private lateinit var headerView: KeywordHeaderView

    private fun initKeyHeaderView() {
        headerView = KeywordHeaderView(this)
        headerView.setHint("请输入部门")
        deptAdapter.addHeaderView(headerView)
    }

    override fun bindIntent() {
        GetDept().toMaybe(this)
                .doOnSuccess { observeKeyword(it.deptData) }
                .map { deptData ->
                    val childDeptList = deptData.deptData.filter { dept -> dept.value.length != 1 }
                    ArrayList<SelectWrapper<Dept>>().apply {
                        childDeptList.forEach { dept -> this.add(SelectWrapper(dept)) }
                    }
                }
                .subscribe { deptAdapter.setNewData(it) }

        observeConfirm()
    }

    private fun observeConfirm() {
        titleBar.setOperation("确认").safeClicks().subscribe {
            deptAdapter.data
                    .filter { wrapper -> wrapper.isSelected }
                    .map { wrapperSelected -> wrapperSelected.t }
                    .let { deptSelected -> RxBus.get().post(DeptSelectResult(tag, deptSelected)) }
            finish()
        }
    }

    private fun observeKeyword(deptList: List<Dept>) {
        RxTextView.textChanges(headerView.etKeyword)
                .map { it.toString() }
                .subscribe { keyword ->
                    deptList.filter { dept -> dept.text.contains(keyword) }
                            .map { dept -> SelectWrapper(dept) }
                            .let { deptAdapter.setNewData(it) }
                }
    }

}
