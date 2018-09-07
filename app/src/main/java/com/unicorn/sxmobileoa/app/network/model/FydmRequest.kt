package com.unicorn.sxmobileoa.app.network.model

import com.unicorn.sxmobileoa.app.Key

open class FydmRequest(busiCode: String, fydm: String) : request(busiCode) {

    init {
        addParameter(Key.fydm, fydm)
    }

}
