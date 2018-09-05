package com.unicorn.sxmobileoa.login.useCase

data class SimpleResponse<Model>(
        val code: String,
        val msg: String
) {
    var message: String? = null
    var result: Model? = null
}