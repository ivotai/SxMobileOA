package com.unicorn.sxmobileoa.simple.court.network

import com.unicorn.sxmobileoa.app.network.model.MaybeRequest

class CourtRequest : MaybeRequest(busiCode = "fyxx") {

    init {
        // 查询所有法院
        addParameter("type", "0")
    }

}