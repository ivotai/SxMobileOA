package com.unicorn.sxmobileoa.main.main.model

import java.io.Serializable

data class MainItem(
        val moduleCode: String = "",
        val flowCode: String = "",
        val spdCode: String = "",
        val text: String,
        var resId: Int ,
        val court: Int
) : Serializable

