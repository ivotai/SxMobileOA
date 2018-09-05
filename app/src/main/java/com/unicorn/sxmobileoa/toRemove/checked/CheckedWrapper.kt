package com.unicorn.sxmobileoa.toRemove.checked

data class CheckedWrapper<MODEL>(
        val actual: MODEL,
        var isChecked: Boolean = false



)