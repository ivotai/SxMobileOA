package com.unicorn.sxmobileoa.remove.business.general.checked

data class CheckedWrapper<MODEL>(
        val actual: MODEL,
        var isChecked: Boolean = false



)