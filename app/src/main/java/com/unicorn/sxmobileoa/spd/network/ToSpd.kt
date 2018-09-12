package com.unicorn.sxmobileoa.spd.network

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.model.Spd

class ToSpd(menu: Menu, dbxx: Dbxx) : BaseUseCase<Spd>() {

    init {
        request = ToSpdRequest(menu, dbxx)
    }

    override fun toResult(json: String): Spd {
        return ComponentHolder.appComponent.getGson().fromJson(json, Spd::class.java)
    }

}