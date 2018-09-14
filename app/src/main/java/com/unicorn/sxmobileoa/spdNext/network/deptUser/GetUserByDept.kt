package com.unicorn.sxmobileoa.spdNext.network.deptUser

import com.unicorn.sxmobileoa.app.network.BaseUseCase

class GetUserByDept(deptId: String) : BaseUseCase<Any>() {

    init {
        request = DeptUserRequest(deptId)
    }

    override fun toResult(json: String): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}