package com.unicorn.sxmobileoa.spyj.network

import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.model.MaybeRequest

class SaveSpdRequest:MaybeRequest("savespd"){

    init {
        val json = ComponentHolder.appComponent.getGson().toJson(Global.spd.spdXx)
        addParameter("spdxxJson",json)
    }

}