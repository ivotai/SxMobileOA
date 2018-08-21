package com.unicorn.sxmobileoa.login

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseFetcher
import com.unicorn.sxmobileoa.app.network.Response
import com.unicorn.sxmobileoa.login.model.LoginParameters

class LoginFetcher(val account: String,val pwd: String) : BaseFetcher<LoginParameters>() {

    override val busiCode: String = "ydbg_userLogin"

    override fun initParameters() {
        parameters.apply {
            put("court_code", "M00")
            put("username", account)
            put("password", pwd)
        }
    }

    override fun map(response: Response): LoginParameters {
        val json = response.parameters.toString()
        return ComponentHolder.appComponent.getGson()
                .fromJson(json, LoginParameters::class.java)
    }

}