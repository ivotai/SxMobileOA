package com.unicorn.sxmobileoa.app

import com.unicorn.sxmobileoa.detail.model.Spd
import com.unicorn.sxmobileoa.detail.model.Spyj
import com.unicorn.sxmobileoa.login.court.model.Court
import com.unicorn.sxmobileoa.login.login.model.LoginInfo
import com.unicorn.sxmobileoa.main.dbxx.model.Dbxx

object Global {

    var ticket: String? = null

    var loginInfo: LoginInfo? = null

    var court: Court? = null

    lateinit var detail: Spd

    lateinit var dbxx:Dbxx

    lateinit var spyj: Spyj
}
