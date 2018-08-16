package com.unicorn.sxmobileoa.app.chongqing

import java.util.*

class LoginFetcher(account: String, pwd: String) : BaseFetcher() {

    override val busiCode: String = ""

    override val parameters = HashMap<String, Any>().apply {
        put("account", account)
        put("pwd", pwd)
    }

}