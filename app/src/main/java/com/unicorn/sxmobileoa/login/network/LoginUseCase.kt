package com.unicorn.sxmobileoa.login.network

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.login.model.LoginInfo

class LoginUseCase(private val username: String, private val password: String) : BaseUseCase<LoginInfo>() {

    override fun createRequest() = LoginRequest(username, password)

    override fun toResult(json: String): LoginInfo = ComponentHolder.appComponent.getGson()
            .fromJson(json, LoginInfo::class.java)

}