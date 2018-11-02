package com.unicorn.sxmobileoa.n.add.network

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.login.model.LoginInfo
import com.unicorn.sxmobileoa.login.network.LoginRequest

class AddQjSpd : BaseUseCase<Any>() {

    init {
        request = AddQjSpdRequest()
    }

    override fun toResult(json: String): Any {
        return ComponentHolder.appComponent.getGson().fromJson(json, Any::class.java)
    }

}