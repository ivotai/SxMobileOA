package com.unicorn.sxmobileoa.spdNext.model

import com.chad.library.adapter.base.entity.IExpandable

data class CourtTree(
        val id: String,
        val text: String,
        val type: String,
        val courtCode: String,
        val courtNo: Int,
        val courtName: String,
        val children: MutableList<DeptTree>
) : IExpandable<DeptTree> {
    override fun getSubItems(): MutableList<DeptTree> {
        return children
    }

    var b = false
    override fun isExpanded() = b

    override fun getLevel() = 0

    override fun setExpanded(expanded: Boolean) {
        b = expanded
    }
}

data class DeptTree(
        val id: String,
        val text: String,
        val type: String,
        val courtNo: Int,
        val orgCode: String,
        val deptNo: Int,
        val deptName: String,
        val children: MutableList<Any>,   // TODO USER DEPT
        val levelCode: String
) : IExpandable<Any> {

    override fun getSubItems() = children

    var b = false

    override fun isExpanded() = b

    override fun getLevel() = 1

    override fun setExpanded(expanded: Boolean) {
        b = expanded
    }

}

data class User(
        val id: String,
        val text: String,
        val type: String,
        val courtCode: String,
        val courtNo: Int,
        val orgCode: String,
        val deptNo: Int,
        val userId: Int,
        val userUuid: String,
        val userFullName: String,
        val checked: Boolean
)