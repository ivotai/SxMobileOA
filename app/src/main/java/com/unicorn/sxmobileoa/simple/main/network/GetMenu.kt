package com.unicorn.sxmobileoa.simple.main.network

import com.google.gson.reflect.TypeToken
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.simple.main.model.ParentMenu

class GetMenu : BaseUseCase<List<ParentMenu>>() {

    init {
        request = MenuRequest()
    }

    override fun toResult(json: String): List<ParentMenu> {
        val type = object : TypeToken<List<ParentMenu>>() {}.type
        return ComponentHolder.appComponent.getGson().fromJson<List<ParentMenu>>(json, type)
    }

}