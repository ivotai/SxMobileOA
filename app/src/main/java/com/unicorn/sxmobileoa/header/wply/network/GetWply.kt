package com.unicorn.sxmobileoa.header.wply.network

import com.unicorn.sxmobileoa.app.network.BaseUseCase

class GetWply() : BaseUseCase<Any>() {

    init {
        request = WplyRequest()
    }

    override fun toResult(json: String): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}