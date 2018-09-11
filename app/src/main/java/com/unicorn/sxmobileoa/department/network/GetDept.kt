package com.unicorn.sxmobileoa.department.network

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase

class GetDept : BaseUseCase<Any>(){
    override fun createRequest() = DeptRequest()

    override fun toResult(json: String): Any {
        return ComponentHolder.appComponent.getGson().fromJson(json,Any::class.java)
    }
}