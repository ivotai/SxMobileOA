package com.unicorn.sxmobileoa.spdNext.network.nextUser

import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.network.model.MaybeRequest
import com.unicorn.sxmobileoa.spd.model.Spd
import com.unicorn.sxmobileoa.spdNext.model.NextTaskSequenceFlow

class NextUserRequest(spd: Spd, nextTaskSequenceFlow: NextTaskSequenceFlow) : MaybeRequest("nextUser") {

    init {
        addParameter("rolesId", nextTaskSequenceFlow.dealPersonRoles)
        addParameter("deptDm", Global.loginInfo!!.deptId)
        addParameter("nextTaskKey", nextTaskSequenceFlow.nextTaskKey)
        addParameter(Key.processInstanceId, spd.spdXx.processInstancesId)
        addParameter(Key.spdid, spd.spdXx.id)

        // 无效参数
        addParameter("userIds", "")
        addParameter("lastCourt", "")
        addParameter("lowerCode", "")
        addParameter("isCjblr", "")
    }

}