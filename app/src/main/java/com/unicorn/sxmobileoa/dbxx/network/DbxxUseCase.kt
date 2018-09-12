package com.unicorn.sxmobileoa.dbxx.network

import com.google.gson.reflect.TypeToken
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.app.ui.page.model.Page
import com.unicorn.sxmobileoa.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.simple.main.model.Menu

class DbxxUseCase(private val pageNo: Int, private val mainItem: Menu) : BaseUseCase<Page<Dbxx>>() {

    override fun createRequest() = DbxxRequest(pageNo, mainItem)

    override fun toResult(json: String): Page<Dbxx> {
        val type = object : TypeToken<Page<Dbxx>>() {}.type
        return ComponentHolder.appComponent.getGson().fromJson<Page<Dbxx>>(json, type)
    }

}