package com.unicorn.sxmobileoa.spd.network

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.spd.model.SaveSptResponse
import com.unicorn.sxmobileoa.spd.model.Spd

class SaveSpd(spd:Spd) : BaseUseCase<SaveSptResponse>() {

    init {
        request = SaveSpdRequest(spd)
    }

    override fun toResult(json: String): SaveSptResponse {
        return ComponentHolder.appComponent.getGson().fromJson(json, SaveSptResponse::class.java)
    }

}
