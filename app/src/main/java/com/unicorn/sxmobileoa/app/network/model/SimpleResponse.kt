package com.unicorn.sxmobileoa.app.network.model

data class SimpleResponse<Model>(
        val code: String,
        val msg: String
) {
    var message: String? = null
    var result: Model? = null
}