package com.unicorn.sxmobileoa.spdNext.model

data class CourtTree(
        val id: String,
        val text: String,
        val type: String,
        val courtCode: String,
        val courtNo: Int,
        val courtName: String,
        val children: List<DeptTree>
)

data class DeptTree(
        val id: String,
        val text: String,
        val type: String,
        val courtNo: Int,
        val orgCode: String,
        val deptNo: Int,
        val deptName: String,
        val children: List<User>,
        val levelCode: String
)

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