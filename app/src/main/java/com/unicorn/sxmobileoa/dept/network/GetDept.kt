package com.unicorn.sxmobileoa.dept.network

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.dept.model.DeptData

class GetDept : BaseUseCase<DeptData>() {

    override fun createRequest() = DeptRequest()

    override fun toResult(json: String): DeptData {
        return ComponentHolder.appComponent.getGson().fromJson(json, DeptData::class.java)
    }

}