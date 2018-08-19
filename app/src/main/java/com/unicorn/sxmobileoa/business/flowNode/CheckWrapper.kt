package com.unicorn.sxmobileoa.business.flowNode

data class CheckWrapper<T>(
        val t: T,
        var isChecked: Boolean = false
)