package com.unicorn.sxmobileoa.spd.network

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.spd.model.Spd
import com.unicorn.sxmobileoa.spd.model.SpdInfo

class SaveSpd(spd:Spd) : BaseUseCase<SpdInfo>() {

    init {
        request = SaveSpdRequest(spd)
    }

    override fun toResult(json: String): SpdInfo {
        return ComponentHolder.appComponent.getGson().fromJson(json, SpdInfo::class.java)
    }

}
