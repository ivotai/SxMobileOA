package com.unicorn.sxmobileoa.spd.network

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.model.MaybeRequest
import com.unicorn.sxmobileoa.spd.model.Spd

class SaveSpdRequest(spd: Spd) : MaybeRequest("savespd") {

    init {
        addParameter("spdxxJson", ComponentHolder.appComponent.getGson().toJson(spd))
    }

}