package com.unicorn.sxmobileoa.app.network.model

// Response 的简化形式，便于 downstream 处理
data class SimpleResponse<Model>(
        val code: String,
        val msg: String,
        var message: String? = null,
        var result: Model? = null
)
