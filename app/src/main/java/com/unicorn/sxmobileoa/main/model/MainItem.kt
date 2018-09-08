package com.unicorn.sxmobileoa.main.model

import java.io.Serializable

// TODO MODIFY NAME
data class MainItem(
        val moduleCode: String = "",
        val flowCode: String = "",
        val spdCode: String = "",
        val text: String,
        val resId: Int
) : Serializable