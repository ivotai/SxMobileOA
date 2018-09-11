package com.unicorn.sxmobileoa.dept.ui

import android.support.v7.widget.LinearLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.app.utils.RxBus
import com.unicorn.sxmobileoa.dept.model.Dept
import com.unicorn.sxmobileoa.dept.model.DeptResult
import com.unicorn.sxmobileoa.dept.model.SelectWrapper
import com.unicorn.sxmobileoa.dept.network.GetDept
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import kotlinx.android.synthetic.main.act_dept.*
import kotlinx.android.synthetic.main.title_bar.*

class DeptAct : BaseAct() {

    override val layoutId = R.layout.act_dept

    private val deptAdapter = DeptAdapter()

    override fun initViews() {
        titleBar.setTitle("选择部门")
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@DeptAct)
            deptAdapter.bindToRecyclerView(this)
            HorizontalDividerItemDecoration.Builder(this@DeptAct)
                    .colorResId(R.color.md_grey_300)
                    .size(1)
                    .build().let { this.addItemDecoration(it) }
        }
    }

    override fun bindIntent() {
        GetDept().toMaybe(this)
                .map {
                    val list = ArrayList<SelectWrapper<Dept>>()
                    it.deptData.forEach { dept -> list.add(SelectWrapper(dept)) }
                    return@map list
                }
                .subscribe {
                    deptAdapter.setNewData(it)
                }

        tvTitle.safeClicks().subscribe {
            val wrapperList = deptAdapter.data
            val deptList = wrapperList
                    .filter { selectWrapper -> selectWrapper.isSelected }
                    .map { wrapper -> wrapper.t }
            val result = DeptResult("zsmc", deptList)
            RxBus.get().post(result)
            finish()
        }
    }

}
