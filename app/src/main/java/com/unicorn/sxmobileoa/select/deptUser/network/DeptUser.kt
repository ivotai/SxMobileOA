package com.unicorn.sxmobileoa.select.deptUser.network

import com.google.gson.reflect.TypeToken
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import counicom.rn.sxmobileoa.select.deptUser.model.DeptUser

class DeptUser(deptId: String) : BaseUseCase<List<DeptUser>>() {

    init {
        request = DeptUserRequest(deptId)
    }

    override fun toResult(json: String): List<DeptUser> {
        val type = object : TypeToken<List<DeptUser>>() {}.type
        return ComponentHolder.appComponent.getGson().fromJson<List<DeptUser>>(json, type)
    }

}