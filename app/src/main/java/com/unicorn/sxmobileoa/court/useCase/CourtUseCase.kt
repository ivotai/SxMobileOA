package com.unicorn.sxmobileoa.court.useCase

import com.google.gson.reflect.TypeToken
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.court.model.Court

class CourtUseCase : BaseUseCase<List<Court>>() {

    override fun createRequest() = CourtRequest()

    override fun toModel(json: String): List<Court> {
        val type = object : TypeToken<List<Court>>() {}.type
        return ComponentHolder.appComponent.getGson().fromJson<List<Court>>(json, type)
    }

}