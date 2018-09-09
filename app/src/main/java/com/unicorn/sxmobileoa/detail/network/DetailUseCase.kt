package com.unicorn.sxmobileoa.detail.network

import com.google.gson.reflect.TypeToken
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
        val type = object : TypeToken<Any>() {}.type
        return ComponentHolder.appComponent.getGson().fromJson<Detail>(json, type)
    }

}