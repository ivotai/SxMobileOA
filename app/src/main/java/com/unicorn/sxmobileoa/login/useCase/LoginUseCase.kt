package com.unicorn.sxmobileoa.login.useCase

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.FydmUseCase
import com.unicorn.sxmobileoa.login.model.LoginInfo

class LoginUseCase(private val username: String, private val password: String) : FydmUseCase<LoginInfo>() {

    override val busiCode = "oalogin"

    override fun addParameters() {
        super.addParameters()
        addParameter("userName", username)
        addParameter("passWord", password)
    }

    override fun toModel(json: String): LoginInfo = ComponentHolder.appComponent.getGson()
            .fromJson(json, LoginInfo::class.java)

}