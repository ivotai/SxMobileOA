package com.unicorn.sxmobileoa.select.deptUser.ui

import android.support.v7.widget.LinearLayoutManager
import com.orhanobut.logger.Logger
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.addDefaultItemDecoration
import com.unicorn.sxmobileoa.app.mess.SelectWrapper
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.select.dept.network.GetDept
import com.unicorn.sxmobileoa.select.deptUser.network.DeptUser
import kotlinx.android.synthetic.main.act_dept_user.*

class DeptUserAct : BaseAct() {

    override fun initViews() {
       initRvDept()
    }

    private val deptAdapter = DeptAdapter()

    private fun initRvDept(){
        rvDept.apply {
            layoutManager = LinearLayoutManager(this@DeptUserAct)
            deptAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration()
        }
    }

    override fun bindIntent() {
        getDept()
    }

    private fun getDept() {
        GetDept().toMaybe(this)
                .map { it.deptData }
                .map { list -> list.map { SelectWrapper(it) } }
                .subscribe { deptAdapter.setNewData(it) }
    }

    private fun getUser(deptId: String) {
        DeptUser(deptId).toMaybe(this).subscribe {
            Logger.e(it.toString())
        }
    }

    override val layoutId = R.layout.act_dept_user

}