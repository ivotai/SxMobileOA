package com.unicorn.sxmobileoa.simple.dbxx.network

import com.google.gson.reflect.TypeToken
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.app.ui.page.model.Page
import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.simple.main.model.Menu

class GetDbxx(private val pageNo: Int, private val menu: Menu) : BaseUseCase<Page<Dbxx>>() {

    init {
        request = DbxxRequest(pageNo,menu)
    }

    override fun toResult(json: String): Page<Dbxx> {
        val type = object : TypeToken<Page<Dbxx>>() {}.type
        return ComponentHolder.appComponent.getGson().fromJson<Page<Dbxx>>(json, type)
    }

}