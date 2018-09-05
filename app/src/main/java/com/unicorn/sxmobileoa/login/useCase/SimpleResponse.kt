package com.unicorn.sxmobileoa.login.useCase

class SimpleResponse<Model>(
        val code: String,
        val msg: String
) {

    var message: String? = null
    var result: Model? = null

}