package com.unicorn.sxmobileoa.app

import com.unicorn.sxmobileoa.spd.model.Spd
import com.unicorn.sxmobileoa.spd.model.Spyj
import com.unicorn.sxmobileoa.simple.court.model.Court
import com.unicorn.sxmobileoa.login.model.LoginInfo
import com.unicorn.sxmobileoa.dbxx.model.Dbxx

object Global {

    var ticket: String? = null

    var loginInfo: LoginInfo? = null

    var court: Court? = null

    lateinit var spd: Spd

    lateinit var dbxx:Dbxx

    lateinit var spyj: Spyj
}
