package com.unicorn.sxmobileoa.business.general

data class CheckedWrapper<MODEL>(
        val actual: MODEL,
        var isChecked: Boolean = false
)