package com.unicorn.sxmobileoa.detail.network

import com.google.gson.reflect.TypeToken
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.app.ui.page.model.Page
import com.unicorn.sxmobileoa.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.dbxx.model.Param

class DetailUseCase(private val moduleCode: String, private val param: Param) : BaseUseCase<Any>() {

    override fun createRequest() = DetailRequest(moduleCode = moduleCode, primaryId = param.primaryId,
            nodeId = param.nodeId, taskId = param.taskId)

    override fun toResult(json: String): Any {
        val type = object : TypeToken<Any>() {}.type
        return ComponentHolder.appComponent.getGson().fromJson<Page<Dbxx>>(json, type)
    }

}