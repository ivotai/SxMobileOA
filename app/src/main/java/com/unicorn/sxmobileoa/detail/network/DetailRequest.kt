package com.unicorn.sxmobileoa.detail.network

import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.network.model.MaybeRequest

class DetailRequest(primaryId: String, moduleCode: String,
                    taskId: String, nodeId: String) : MaybeRequest("tospd") {

    init {
        addParameter(Key.primaryId, primaryId)
        addParameter(Key.moduleCode, moduleCode)
        addParameter(Key.taskId, taskId)
        addParameter(Key.nodeId, nodeId)
    }

}