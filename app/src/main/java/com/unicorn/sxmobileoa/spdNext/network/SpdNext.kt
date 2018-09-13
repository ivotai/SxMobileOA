package com.unicorn.sxmobileoa.spdNext.network

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.model.Spd
import com.unicorn.sxmobileoa.spdNext.model.SpdNextResponse

class SpdNext(menu: Menu, dbxx: Dbxx,spd:Spd) : BaseUseCase<SpdNextResponse>() {

    init {
        request = SpdNextRequest(menu, dbxx,spd)
    }

    override fun toResult(json: String): SpdNextResponse {
        return ComponentHolder.appComponent.getGson().fromJson(json, SpdNextResponse::class.java)
    }

}