package com.unicorn.sxmobileoa.n.add.network

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase

class AddQjsq : BaseUseCase<Any>() {

    init {
        request = AddQjsqRequest()
    }

    override fun toResult(json: String): Any {
        return ComponentHolder.appComponent.getGson().fromJson(json, Any::class.java)
    }

}