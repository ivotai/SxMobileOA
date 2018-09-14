package com.unicorn.sxmobileoa.simple.code.network

import com.google.gson.reflect.TypeToken
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase

class GetCode(code: String) : BaseUseCase<Any>() {

    init {
        request = CodeRequest(code)
    }

    override fun toResult(json: String): Any {
        val type = object : TypeToken<List<Any>>() {}.type
        return ComponentHolder.appComponent.getGson().fromJson<List<Any>>(json, type)
    }
}