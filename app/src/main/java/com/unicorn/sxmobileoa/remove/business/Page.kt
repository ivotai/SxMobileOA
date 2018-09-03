package com.unicorn.sxmobileoa.remove.business

data class Page<T>(
        val total:Int,
        val rows: List<T>
)