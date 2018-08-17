package com.unicorn.sxmobileoa.app.chongqing

import java.util.*

class LoginFetcher(account: String, pwd: String) : BaseFetcher() {

    override val busiCode: String = "ydbg_app_title"

    override val parameters = HashMap<String, Any>().apply {
        put("fydm", "R00")
        put("userid", "111")
        put("password", "111")
//        put("pwd", pwd)
    }

}