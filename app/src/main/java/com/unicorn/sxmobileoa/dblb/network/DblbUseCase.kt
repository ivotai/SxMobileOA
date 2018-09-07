package com.unicorn.sxmobileoa.dblb.network

import com.google.gson.reflect.TypeToken
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase

class DblbUseCase(private val pageNo: Int) : BaseUseCase<List<Any>>() {

    override fun createRequest() = DblbRequest(pageNo)

    override fun toModel(json: String): List<Any> {
        val type = object : TypeToken<List<Any>>() {}.type
        return ComponentHolder.appComponent.getGson().fromJson<List<Any>>(json, type)
    }

}