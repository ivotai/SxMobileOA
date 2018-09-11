package com.unicorn.sxmobileoa.department.ui

import android.support.v7.widget.LinearLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.department.network.GetDept
import kotlinx.android.synthetic.main.act_dept.*

class DeptAct : BaseAct() {

    override val layoutId= R.layout.act_dept

    val deptAdapter = DeptAdapter()

    override fun initViews() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@DeptAct)
            deptAdapter.bindToRecyclerView(this)
        }
    }

    override fun bindIntent() {
        GetDept().toMaybe(this).subscribe {

        }
    }

}
