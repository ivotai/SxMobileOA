package com.unicorn.sxmobileoa.simple.deptUser.ui

import android.support.v7.widget.LinearLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.addDefaultItemDecoration
import com.unicorn.sxmobileoa.app.mess.SelectWrapper
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.simple.dept.network.GetDept
import kotlinx.android.synthetic.main.act_dept_user.*

class DeptUserAct : BaseAct() {

    override val layoutId = R.layout.act_dept_user

    val deptAdapter  = DeptAdapter()

    override fun initViews() {
        rvDept.apply {
            layoutManager = LinearLayoutManager(this@DeptUserAct)
            deptAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration()
        }
    }

    override fun bindIntent() {
        GetDept().toMaybe(this)
                .map { it.deptData }
                .map { list -> list.map { SelectWrapper(it) } }
                .subscribe {t->
            deptAdapter.setNewData(t)
        }
    }
}