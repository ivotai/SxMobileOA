package com.unicorn.sxmobileoa.login

import com.google.gson.Gson
import com.unicorn.sxmobileoa.app.chongqing.ParametersFetcher
import com.unicorn.sxmobileoa.app.chongqing.Response
import java.util.*

class LoginFetcher(account: String, pwd: String) : ParametersFetcher<LoginParameters>() {

    override val busiCode: String = "ydbg_userLogin"

    override val parameters = HashMap<String, Any>().apply {
        put("court_code", "R00")
        put("username", account)
        put("password", pwd)
    }

    override fun map(response: Response): LoginParameters {
        val json = response.parameters.toString()
        return Gson().fromJson(json, LoginParameters::class.java)
    }

}