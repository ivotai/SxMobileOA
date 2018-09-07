package com.unicorn.sxmobileoa.app.network.model

import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.Key

open class MaybeRequest(busiCode: String) : request(busiCode) {

    init {
        Global.court?.dm?.let { addParameter(Key.fydm, it) }
        // TODO DELETE
        Global.loginInfo?.userId?.let { addParameter(Key.userId, it) }
    }

}
