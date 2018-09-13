package com.unicorn.sxmobileoa.simple.dept.model

// key表示由哪个textView发起的部门选择
// result表示选择结果，由，号分割
data class DeptResult(
        val key: String,
        val result: String
)