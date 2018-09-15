package com.unicorn.sxmobileoa.select.deptUser.network

import com.google.gson.reflect.TypeToken
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import counicom.rn.sxmobileoa.select.deptUser.model.RealUser

class DeptUser(deptId: String) : BaseUseCase<List<RealUser>>() {

    init {
        request = DeptUserRequest(deptId)
    }

    override fun toResult(json: String): List<RealUser> {
        val type = object : TypeToken<List<RealUser>>() {}.type
        return ComponentHolder.appComponent.getGson().fromJson<List<RealUser>>(json, type)
    }

}