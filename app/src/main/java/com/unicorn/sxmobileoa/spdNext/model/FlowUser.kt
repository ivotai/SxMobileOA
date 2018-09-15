package com.unicorn.sxmobileoa.spdNext.model

data class FlowUser(
    val id: String,     // 传这个
    val uuid: String,
    val courtCode: String,
    val courtStdNo: String,
    val courtNo: Int,
    val deptNo: Int,
    val userNo: String,
    val username: String,
    val fullname: String,
    val deptOrgCode: String,
    val sortNo: String,
    val fyjc: String
)