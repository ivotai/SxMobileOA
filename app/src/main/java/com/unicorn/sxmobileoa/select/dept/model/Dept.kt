package com.unicorn.sxmobileoa.select.dept.model

import com.chad.library.adapter.base.entity.IExpandable
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.unicorn.sxmobileoa.select.deptUser.model.RealUser
import com.unicorn.sxmobileoa.select.deptUser.ui.DeptUserAdapter

data class Dept(
        val id: String,
        var text: String,
        val level0: Int,
        val levelCode: String,
        val userList: MutableList<RealUser>?
) : IExpandable<RealUser>, MultiItemEntity {

    override fun getSubItems() = userList

    var b = false

    override fun isExpanded() = b

    override fun getLevel() = 0

    override fun setExpanded(expanded: Boolean) {
        b = expanded
    }

    override fun getItemType() = DeptUserAdapter.type_dept

}