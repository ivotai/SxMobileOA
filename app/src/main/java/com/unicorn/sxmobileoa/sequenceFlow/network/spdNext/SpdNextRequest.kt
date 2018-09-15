package com.unicorn.sxmobileoa.sequenceFlow.network.spdNext

import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.network.model.MaybeRequest
import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.model.Spd

class SpdNextRequest(menu: Menu, dbxx: Dbxx,spd: Spd) : MaybeRequest("spdnext") {

    init {
        addParameter(Key.moduleCode, menu.moduleCode)
        addParameter(Key.flowCode, menu.flowCode)
        addParameter(Key.primaryId, dbxx.param.primaryId)
        addParameter(Key.currentTaskId, dbxx.param.taskId)
        addParameter(Key.nodeId, dbxx.param.nodeId)
        addParameter(Key.processInstanceId, spd.spdXx.processInstancesId)
    }

}