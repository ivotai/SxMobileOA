package com.unicorn.sxmobileoa.sequenceFlow.network.spdNext

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.sequenceFlow.model.SpdNextResponse
import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.spd.model.Spd

class SpdNext( dbxx: Dbxx,spd:Spd) : BaseUseCase<SpdNextResponse>() {

    init {
        request = SpdNextRequest( dbxx, spd)
    }

    override fun toResult(json: String): SpdNextResponse {
        return ComponentHolder.appComponent.getGson().fromJson(json, SpdNextResponse::class.java)
    }

}