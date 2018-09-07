package com.unicorn.sxmobileoa.app.ui.page

data class Page<Model>(
        val rows: List<Model>,
        val total: Int
)