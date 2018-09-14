package com.unicorn.sxmobileoa.spdNext.network.deptUser

import com.unicorn.sxmobileoa.app.network.model.MaybeRequest

class DeptUserRequest(deptId:String):MaybeRequest("userList"){

    init {
        addParameter("bmbm",deptId)
    }

}