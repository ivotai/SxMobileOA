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
import com.unicorn.sxmobileoa.simple.dept.model.Dept
import com.unicorn.sxmobileoa.simple.dept.model.DeptResult
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
        clickConfirm()
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
                .map { it.deptData }
                // 过滤父节点
                .map { it.filter { dept -> dept.value.length != 1 } }
                .doOnSuccess {
                    addKeywordHeaderView()
                    observeKeyword(it)
                }
                .map { it.map { dept -> SelectWrapper(dept) } }
                .subscribe { t ->
                    deptAdapter.setNewData(t)
                }
    }

    private fun observeKeyword(deptList: List<Dept>) {
        headerView.etKeyword.textChanges()
                .subscribe { keyword ->
                    deptList.filter { dept -> dept.text.contains(keyword) }
                            .map { dept -> SelectWrapper(dept) }
                            .let { deptAdapter.setNewData(it) }
                }
    }

    private fun clickConfirm() {
        titleBar.setOperation("确认").safeClicks().subscribe {
            deptAdapter.data
                    .filter { wrapper -> wrapper.isSelected }
                    .map { wrapperSelected -> wrapperSelected.t }
                    .let { deptListSelected ->
                        val result = deptListSelected.joinToString(",") { dept -> dept.text }
                        RxBus.get().post(DeptResult(model.key, result))
                    }
            finish()
        }
    }

    @DartModel
    lateinit var model: DeptActNavigationModel

    override val layoutId = R.layout.act_title_recycler

}
