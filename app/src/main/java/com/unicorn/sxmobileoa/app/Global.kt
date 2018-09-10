package com.unicorn.sxmobileoa.app

import com.unicorn.sxmobileoa.detail.model.Detail
import com.unicorn.sxmobileoa.login.court.model.Court
import com.unicorn.sxmobileoa.login.login.model.LoginInfo

object Global {

    var ticket: String? = null

    var loginInfo: LoginInfo? = null

    var court: Court? = null

    lateinit var detail: Detail

}
