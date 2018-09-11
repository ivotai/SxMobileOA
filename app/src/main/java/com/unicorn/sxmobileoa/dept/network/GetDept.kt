package com.unicorn.sxmobileoa.dept.network

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.dept.model.DeptResponse

class GetDept : BaseUseCase<DeptResponse>(){

    override fun createRequest() = DeptRequest()

    override fun toResult(json: String): DeptResponse {
        return ComponentHolder.appComponent.getGson().fromJson(json,DeptResponse::class.java)
    }

}