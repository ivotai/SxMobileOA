package com.unicorn.sxmobileoa.spdNext.network.user

import com.google.gson.reflect.TypeToken
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.spdNext.model.CourtTree

class GetUser : BaseUseCase<List<CourtTree>>() {

    init {
        request = UserRequest()
    }

    override fun toResult(json: String): List<CourtTree> {
        val type = object : TypeToken<List<CourtTree>>() {}.type
        return ComponentHolder.appComponent.getGson().fromJson<List<CourtTree>>(json, type)
    }

}