package com.unicorn.sxmobileoa.spd.network

import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.network.model.MaybeRequest
import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.simple.main.model.Menu

class ToSpdRequest(menu: Menu, dbxx: Dbxx) : MaybeRequest("tospd") {

    init {
        addParameter(Key.moduleCode, menu.moduleCode)
        addParameter(Key.primaryId, dbxx.param.primaryId)
        addParameter(Key.taskId, dbxx.param.taskId)
        addParameter(Key.nodeId, dbxx.param.nodeId)
    }

}