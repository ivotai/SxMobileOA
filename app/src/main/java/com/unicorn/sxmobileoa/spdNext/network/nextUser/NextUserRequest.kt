package com.unicorn.sxmobileoa.spdNext.network.nextUser

import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.network.model.MaybeRequest
import com.unicorn.sxmobileoa.spd.model.Spd
import com.unicorn.sxmobileoa.spdNext.model.NextTaskSequenceFlow

class NextUserRequest(spd: Spd, flow: NextTaskSequenceFlow) : MaybeRequest("nextUser") {

    init {
        addParameter("rolesId", flow.dealPersonRoles)
        addParameter("isCjblr", flow.isCjblr)
        addParameter("lastCourt", flow.lastCourt)
        addParameter("nextTaskKey", flow.nextTaskKey)
        addParameter("deptDm", Global.loginInfo!!.deptId)
        addParameter(Key.processInstanceId, spd.spdXx.processInstancesId)
        addParameter(Key.spdid, spd.spdXx.id)

        val userIds = when (flow.dealPerson) {
            "1" -> ""
            "2" -> when (flow.dealPersonType) {
                "1" -> flow.startPersonId
                "2" -> Global.loginInfo!!.userId
                "3" -> flow.dealPersonId
                else -> ""
            }
            else -> ""
        }
        addParameter("userIds", userIds)

        val orgCodes = if (flow.dealPerson != "3") ""
        else when (flow.dealPersonRolesWayDep) {
            "1" -> Global.loginInfo!!.deptId
            "2" -> flow.dealPersonRolesWayDep
            "3" -> ""
            else -> ""
        }
        addParameter("orgCodes", orgCodes)

        addParameter("lowerCode", "")
    }

}