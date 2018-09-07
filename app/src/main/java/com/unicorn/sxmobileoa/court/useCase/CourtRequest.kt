package com.unicorn.sxmobileoa.court.useCase

import com.unicorn.sxmobileoa.app.network.model.MaybeRequest

//@Root(name = "request")
class CourtRequest : MaybeRequest(busiCode = "fyxx") {

    init {
        // type 0 表示查询所有法院
        addParameter("type", "0")
    }

}
