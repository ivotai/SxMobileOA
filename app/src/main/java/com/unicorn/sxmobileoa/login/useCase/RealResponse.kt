package com.unicorn.sxmobileoa.login.useCase

class RealResponse<Model>(
        val code: String,
        val msg: String
        ) {

    val message: String? = null
    val result: Model? = null

}