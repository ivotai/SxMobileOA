package com.unicorn.sxmobileoa.login

import com.google.gson.Gson
import com.unicorn.sxmobileoa.app.chongqing.BaseFetcher
import com.unicorn.sxmobileoa.app.chongqing.Response
import java.util.*

class LoginFetcher(account: String, pwd: String) : BaseFetcher<LoginResponse>() {

    override val busiCode: String = "ydbg_userLogin"

    override val parameters = HashMap<String, Any>().apply {
        put("court_code", "R00")
        put("username", account)
        put("password", pwd)
    }

    override fun map(response: Response): LoginResponse {
        val json = response.parameters.toString()
        return Gson().fromJson(json, LoginResponse::class.java)
    }

}