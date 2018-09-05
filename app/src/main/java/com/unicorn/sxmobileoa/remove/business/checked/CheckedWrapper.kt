package com.unicorn.sxmobileoa.remove.business.checked

data class CheckedWrapper<MODEL>(
        val actual: MODEL,
        var isChecked: Boolean = false



)