package com.unicorn.sxmobileoa.dept.ui

import android.support.v7.widget.LinearLayoutManager
import android.widget.TextView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.addDefaultItemDecotation
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.app.utils.RxBus
import com.unicorn.sxmobileoa.dept.model.Dept
import com.unicorn.sxmobileoa.dept.model.DeptSelectResult
import com.unicorn.sxmobileoa.dept.network.GetDept
import com.unicorn.sxmobileoa.g.KeywordHeaderView
import com.unicorn.sxmobileoa.g.SelectWrapper
import kotlinx.android.synthetic.main.act_titlebar_recyclerview.*
import kotlinx.android.synthetic.main.title_bar.view.*

class DeptAct : BaseAct() {

    lateinit var tag: String

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
            addDefaultItemDecotation()
        }
        addKeyHeaderView()
    }

    private lateinit var etKeyword: TextView

    private fun addKeyHeaderView() {
        val keywordHeaderView = KeywordHeaderView(this)
        deptAdapter.addHeaderView(keywordHeaderView)
        etKeyword = keywordHeaderView.etKeyword
        keywordHeaderView.setHint("请输入部门")
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
        // TODO
        observeSth()
    }

    private fun observeSth() {
        titleBar.tvTitle.safeClicks().subscribe {
            deptAdapter.data
                    .filter { wrapper -> wrapper.isSelected }
                    .map { wrapperSelected -> wrapperSelected.t }
                    .let { deptListSelected -> RxBus.get().post(DeptSelectResult(tag, deptListSelected)) }
            finish()
        }
    }

    private fun observeKeyword(deptList: List<Dept>) {
        RxTextView.textChanges(etKeyword)
                .map { it.toString() }
                .subscribe { keyword ->
                    deptList.filter { dept -> dept.text.contains(keyword) }
                            .map { dept -> SelectWrapper(dept) }
                            .let { deptAdapter.setNewData(it) }
                }
    }

}
