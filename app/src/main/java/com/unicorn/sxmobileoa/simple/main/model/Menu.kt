package com.unicorn.sxmobileoa.simple.main.model

import java.io.Serializable

data class Menu(
        val moduleCode: String,
        val flowCode: String,
        val spdCode: String,
        val text: String,
        val count: String,
        var resId: Int
) : Serializable

