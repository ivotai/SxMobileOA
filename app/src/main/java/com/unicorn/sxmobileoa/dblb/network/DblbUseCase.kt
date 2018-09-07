package com.unicorn.sxmobileoa.dblb.network

import com.google.gson.reflect.TypeToken
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.app.ui.page.model.Page
import com.unicorn.sxmobileoa.dblb.model.Dblb

class DblbUseCase(private val pageNo: Int) : BaseUseCase<Page<Dblb>>() {

    override fun createRequest() = DblbRequest(pageNo)

    override fun toResult(json: String): Page<Dblb> {
        val type = object : TypeToken<Page<List<Dblb>>>() {}.type
        return ComponentHolder.appComponent.getGson().fromJson<Page<Dblb>>(json, type)
    }

}