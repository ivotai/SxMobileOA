package com.unicorn.sxmobileoa.login.model

data class UserInfo(
    val password: String,
    val id: String,
    val username: String,
    val court_code: String,
    val fullname: String,
    val deptNo: String
)