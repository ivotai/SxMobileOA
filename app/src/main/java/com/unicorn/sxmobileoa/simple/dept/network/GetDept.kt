package com.unicorn.sxmobileoa.simple.dept.network

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.simple.dept.model.DeptData

class GetDept : BaseUseCase<DeptData>() {

    init {
        request = DeptRequest()
    }

    override fun toResult(json: String): DeptData {
        return ComponentHolder.appComponent.getGson().fromJson(json, DeptData::class.java)
    }

}