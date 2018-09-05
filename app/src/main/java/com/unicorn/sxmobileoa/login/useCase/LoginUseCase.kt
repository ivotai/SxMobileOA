package com.unicorn.sxmobileoa.login.useCase

import com.unicorn.sxmobileoa.login.BaseUseCase


class LoginUseCase(private val username: String, private val password: String) : BaseUseCase() {

    override val busiCode = "oalogin"

    override fun addParameters() {
        addParameter("userName", username)
        addParameter("passWord", password)
    }



}