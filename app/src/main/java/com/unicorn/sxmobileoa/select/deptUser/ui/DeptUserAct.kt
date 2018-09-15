package com.unicorn.sxmobileoa.select.deptUser.ui

import android.support.v7.widget.LinearLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.addDefaultItemDecoration
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.select.dept.network.GetDept
import com.unicorn.sxmobileoa.select.deptUser.model.DeptUserActNavigationModel
import dart.DartModel
import kotlinx.android.synthetic.main.act_title_recycler.*

class DeptUserAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("选择人员")
        initRecyclerView()
    }

    private val mAdapter = DeptUserAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@DeptUserAct)
            mAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration()
        }
    }

    override fun bindIntent() {
        getDept()
//        clickOperation()
    }

    private fun getDept() {
        GetDept().toMaybe(this)
                .map { it.deptData }
                // TODO 手动排序
//                .map { it.sortedBy { dept -> dept.levelCode } }
//                .doOnSuccess { textChangeKeyword(it) }
//                .map { it.map { dept -> SelectWrapper(dept) } }
                .subscribe { mAdapter.setNewData(it) }
    }

//    private fun textChangeKeyword(allDept: List<Dept>) {
//        KeywordHeaderView(this).apply {
//            setHint("请输入部门")
//            mAdapter.addHeaderView(this)
//        }.etKeyword.textChanges()
//                .subscribe { keyword ->
//                    allDept.filter { dept -> dept.text.contains(keyword) }
//                            .map { dept -> SelectWrapper(dept) }
//                            .let { mAdapter.setNewData(it) }
//                }
//    }
//
//    private fun clickOperation() {
//        titleBar.setOperation("确认").safeClicks().subscribe { _ ->
//            mAdapter.data
//                    .filter { it.isSelected }
//                    .map { it.t }
//                    .let { listSelected ->
//                        val result = listSelected.joinToString(",") { it.text }
//                        RxBus.get().post(SelectResult(model.key, result))
//                    }
//            finish()
//        }
//    }

    @DartModel
    lateinit var model: DeptUserActNavigationModel

    override val layoutId = R.layout.act_title_recycler

}