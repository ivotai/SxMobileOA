package com.unicorn.sxmobileoa.login.useCase

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.login.BaseUseCase
import com.unicorn.sxmobileoa.login.model.LoginInfo


class LoginUseCase(private val username: String, private val password: String) : BaseUseCase<LoginInfo>() {

    override val busiCode = "oalogin"

    override fun addParameters() {
        addParameter("userName", username)
        addParameter("passWord", password)
    }

    override fun map(result: String): LoginInfo = ComponentHolder.appComponent.getGson()
            .fromJson(result, LoginInfo::class.java)

}