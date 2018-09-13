package com.unicorn.sxmobileoa.simple.dept.ui

import android.support.v7.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.widget.RxTextView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.addDefaultItemDecoration
import com.unicorn.sxmobileoa.app.mess.KeywordHeaderView
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.mess.SelectWrapper
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.simple.dept.model.Dept
import com.unicorn.sxmobileoa.simple.dept.model.DeptResult
import com.unicorn.sxmobileoa.simple.dept.network.GetDept
import dart.DartModel
import io.reactivex.Observable
import kotlinx.android.synthetic.main.act_title_recycler.*

class DeptAct : BaseAct() {

    @DartModel
     lateinit var model: DeptActNavigationModel

    override val layoutId = R.layout.act_title_recycler

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
        addHeaderView()
    }

    private lateinit var headerView: KeywordHeaderView

    private fun addHeaderView() {
        headerView = KeywordHeaderView(this)
        headerView.setHint("请输入部门")
        deptAdapter.addHeaderView(headerView)
    }

    override fun bindIntent() {
        GetDept().toMaybe(this).toObservable()
                .flatMap { Observable.fromIterable(it.deptData) }
                // 过滤父节点
                .filter { it.value.length != 1 }
                .toList()
                .doOnSuccess { observeKeyword(it) }
                .map { deptList ->
                    ArrayList<SelectWrapper<Dept>>().apply {
                        deptList.forEach { dept -> this.add(SelectWrapper(dept)) }
                    }
                }
                .subscribe { t -> deptAdapter.setNewData(t) }

        clickConfirm()
    }

    private fun clickConfirm() {
        titleBar.setOperation("确认").safeClicks().subscribe {
            deptAdapter.data
                    .filter { wrapper -> wrapper.isSelected }
                    .map { wrapperSelected -> wrapperSelected.t }
                    .let { deptSelected ->
                        val result = deptSelected.joinToString(",") { dept -> dept.text }
                        RxBus.get().post(DeptResult(model.key, result))
                    }
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
