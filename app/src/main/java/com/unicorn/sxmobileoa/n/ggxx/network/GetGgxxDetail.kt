package com.unicorn.sxmobileoa.n.ggxx.network

import com.google.gson.reflect.TypeToken
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.n.ggxx.model.Ggxx

class GetGgxxDetail(id:  String) : BaseUseCase<Ggxx>() {

    init {
        request = GgxxDetialRequest(id)
    }

    override fun toResult(json: String): Ggxx {
        val type = object : TypeToken<Ggxx>() {}.type
        return ComponentHolder.appComponent.getGson().fromJson<Ggxx>(json, type)
    }

}