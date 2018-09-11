package com.unicorn.sxmobileoa.dept.ui

import android.support.v7.widget.LinearLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.addDefaultItemDecotation
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.app.utils.RxBus
import com.unicorn.sxmobileoa.dept.model.Dept
import com.unicorn.sxmobileoa.dept.model.DeptResult
import com.unicorn.sxmobileoa.dept.model.SelectWrapper
import com.unicorn.sxmobileoa.dept.network.GetDept
import kotlinx.android.synthetic.main.act_dept.*
import kotlinx.android.synthetic.main.title_bar.*

class DeptAct : BaseAct() {

    lateinit var tag: String

    override fun initArguments() {
        tag = intent.getStringExtra(Key.tag)
    }

    override val layoutId = R.layout.act_dept

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
    }

    override fun bindIntent() {
        getDept()
        tvTitle.safeClicks().subscribe {
            val wrapperList = deptAdapter.data
            val deptList = wrapperList
                    .filter { wrapper -> wrapper.isSelected }
                    .map { wrapper -> wrapper.t }
            RxBus.get().post(DeptResult(tag, deptList))
            finish()
        }
    }

    private fun getDept() {
        GetDept().toMaybe(this)
                .map { deptResponse ->
                    ArrayList<SelectWrapper<Dept>>().apply {
                        deptResponse.deptData.forEach { dept -> this.add(SelectWrapper(dept)) }
                    }
                }
                .subscribe { deptAdapter.setNewData(it) }
    }

}
