package com.unicorn.sxmobileoa.court.useCase

import com.unicorn.sxmobileoa.app.network.model.FydmRequest

//@Root(name = "request")
class CourtRequest : FydmRequest(busiCode = "fyxx", fydm = "") {

    init {
        // type 0 表示查询所有法院
        addParameter("type", "0")
    }

}
