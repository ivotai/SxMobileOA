package com.unicorn.sxmobileoa.login

import com.unicorn.sxmobileoa.app.chongqing.BaseFetcher
import com.unicorn.sxmobileoa.app.chongqing.Response
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import java.util.*

class LoginFetcher(account: String, pwd: String) : BaseFetcher<LoginParameters>() {

    override val busiCode: String = "ydbg_userLogin"

    override val parameters = HashMap<String, Any>().apply {
        put("court_code", "R00")
        put("username", account)
        put("password", pwd)
    }

    override fun map(response: Response): LoginParameters {
        val json = response.parameters.toString()
        return ComponentHolder.appComponent.getGson()
                .fromJson(json, LoginParameters::class.java)
    }

}