package com.unicorn.sxmobileoa.select.deptUser.network

import com.unicorn.sxmobileoa.app.network.BaseUseCase

class DeptUser(deptId: String) : BaseUseCase<Any>() {

    init {
        request = DeptUserRequest(deptId)
    }

    override fun toResult(json: String): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}