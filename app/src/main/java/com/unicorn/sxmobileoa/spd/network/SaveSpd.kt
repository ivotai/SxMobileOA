package com.unicorn.sxmobileoa.spd.network

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.spd.model.Spd

class SaveSpd(spd:Spd) : BaseUseCase<Any>() {

    init {
        request = SaveSpdRequest(spd)
    }

    override fun toResult(json: String): Any {
        return ComponentHolder.appComponent.getGson().fromJson(json, Any::class.java)
    }

}
