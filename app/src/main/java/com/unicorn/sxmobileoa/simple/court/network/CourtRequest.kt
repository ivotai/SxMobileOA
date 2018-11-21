package com.unicorn.sxmobileoa.simple.court.network

import com.unicorn.sxmobileoa.app.network.model.MaybeRequest

class CourtRequest : MaybeRequest(busiCode = "fyxx") {

    init {
        // 查询所有法院     0
        // 中院和基层法院   2
         addParameter("type", "2")
    }

}
