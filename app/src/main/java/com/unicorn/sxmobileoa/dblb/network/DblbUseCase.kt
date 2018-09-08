package com.unicorn.sxmobileoa.dblb.network

import com.google.gson.reflect.TypeToken
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.app.ui.page.model.Page
import com.unicorn.sxmobileoa.dblb.model.Dblb
import com.unicorn.sxmobileoa.main.model.MainItem

class DblbUseCase(private val pageNo: Int, private val mainItem: MainItem) : BaseUseCase<Page<Dblb>>() {

    override fun createRequest() = DblbRequest(pageNo, mainItem)

    override fun toResult(json: String): Page<Dblb> {
        val type = object : TypeToken<Page<Dblb>>() {}.type
        return ComponentHolder.appComponent.getGson().fromJson<Page<Dblb>>(json, type)
    }

}