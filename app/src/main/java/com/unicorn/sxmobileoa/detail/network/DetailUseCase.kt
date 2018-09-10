package com.unicorn.sxmobileoa.detail.network

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.detail.model.Detail
import com.unicorn.sxmobileoa.main.dbxx.model.Dbxx

class DetailUseCase(private val dbxx: Dbxx) : BaseUseCase<Detail>() {

    override fun createRequest() = DetailRequest(
            moduleCode = dbxx.mainItem!!.moduleCode,
            primaryId = dbxx.param.primaryId,
            nodeId = dbxx.param.nodeId,
            taskId = dbxx.param.taskId
    )

    override fun toResult(json: String): Detail {
        return ComponentHolder.appComponent.getGson().fromJson(json, Detail::class.java)
    }

}