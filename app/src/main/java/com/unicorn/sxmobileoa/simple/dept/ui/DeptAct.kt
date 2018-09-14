package com.unicorn.sxmobileoa.simple.dept.ui

import android.support.v7.widget.LinearLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.addDefaultItemDecoration
import com.unicorn.sxmobileoa.app.mess.KeywordHeaderView
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.mess.SelectWrapper
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.textChanges
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.select.model.SelectResult
import com.unicorn.sxmobileoa.simple.dept.model.Dept
import com.unicorn.sxmobileoa.simple.dept.network.GetDept
import dart.DartModel
import kotlinx.android.synthetic.main.act_title_recycler.*

class DeptAct : BaseAct() {

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
    }

    override fun bindIntent() {
        getDept()
        clickOperation()
    }

    private lateinit var headerView: KeywordHeaderView

    private fun addKeywordHeaderView() {
        headerView = KeywordHeaderView(this).apply {
            setHint("请输入部门")
            deptAdapter.addHeaderView(this)
        }
    }

    private fun getDept() {
        GetDept().toMaybe(this)
                .map { deptData -> deptData.deptData
                        .map { if(it.isFather) it.text = "父节点 ${it.text}" }
                        deptData.deptData
                }
                // 过滤父节点
//                .map { deptList ->
//                    deptList.map { if(it.isFather) it.text = "父节点 ${it.text}" }
//
//                    deptList.filter { dept -> dept.isFather }
//                }
                .doOnSuccess {
                    addKeywordHeaderView()
                    observeKeyword(it)
                }
                .map { it.map { dept -> SelectWrapper(dept) } }
                .subscribe { t ->
                    deptAdapter.setNewData(t)
                }
    }

    private fun observeKeyword(allDept: List<Dept>) {
        headerView.etKeyword.textChanges()
                .subscribe { keyword ->
                    allDept.filter { dept -> dept.text.contains(keyword) }
                            .map { dept -> SelectWrapper(dept) }
                            .let { deptAdapter.setNewData(it) }
                }
    }

    private fun clickOperation() {
        titleBar.setOperation("确认").safeClicks().subscribe {
            deptAdapter.data
                    .filter { wrapper -> wrapper.isSelected }
                    .map { wrapperSelected -> wrapperSelected.t }
                    .let { deptListSelected ->
                        val result = deptListSelected.joinToString(",") { dept -> dept.text }
                        RxBus.get().post(SelectResult(model.key, result))
                    }
            finish()
        }
    }

    @DartModel
    lateinit var model: DeptActNavigationModel

    override val layoutId = R.layout.act_title_recycler

}
