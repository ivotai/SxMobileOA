package com.unicorn.sxmobileoa.spyj.network

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase

class SaveSpd : BaseUseCase<Any>() {

    init {
        request = SaveSpdRequest()
    }

    override fun toResult(json: String): Any {
        return ComponentHolder.appComponent.getGson().fromJson(json, Any::class.java)
    }

}
