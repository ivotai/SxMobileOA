package com.unicorn.sxmobileoa.app.chongqing

import java.util.*

class LoginUseCase(account: String, pwd: String) : UseCase() {

    override val busiCode: String = ""

    override val parameters = HashMap<String, Any>().apply {
        put("account", account)
        put("pwd", pwd)
    }

}