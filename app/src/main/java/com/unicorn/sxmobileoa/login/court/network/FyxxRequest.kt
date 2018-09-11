package com.unicorn.sxmobileoa.login.court.network

import com.unicorn.sxmobileoa.app.network.model.MaybeRequest

class FyxxRequest : MaybeRequest(busiCode = "fyxx") {

    init {
        // 查询所有法院
        addParameter("type", "0")
    }

}
