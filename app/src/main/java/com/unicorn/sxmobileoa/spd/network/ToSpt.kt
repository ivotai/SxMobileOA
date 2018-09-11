package com.unicorn.sxmobileoa.spd.network

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.spd.model.Spd

class ToSpt(private val dbxx: Dbxx) : BaseUseCase<Spd>() {

    override fun createRequest() = ToSpdRequest(
            moduleCode = dbxx.mainItem!!.moduleCode,
            primaryId = dbxx.param.primaryId,
            nodeId = dbxx.param.nodeId,
            taskId = dbxx.param.taskId
    )

    override fun toResult(json: String): Spd {
        return ComponentHolder.appComponent.getGson().fromJson(json, Spd::class.java)
    }

}