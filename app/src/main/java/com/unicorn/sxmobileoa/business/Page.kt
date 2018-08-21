package com.unicorn.sxmobileoa.business

data class Page<T>(
        val total:Int,
        val rows: List<T>
)