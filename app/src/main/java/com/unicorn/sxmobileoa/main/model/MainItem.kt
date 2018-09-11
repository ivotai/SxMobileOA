package com.unicorn.sxmobileoa.main.model

import java.io.Serializable

data class MainItem(
        val moduleCode: String ,
        val flowCode: String ,
        val spdCode: String ,
        val text: String,
        var resId: Int ,
        val count: Int
) : Serializable

