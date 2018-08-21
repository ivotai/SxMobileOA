package com.unicorn.sxmobileoa.business.gwgl

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseFetcher
import com.unicorn.sxmobileoa.app.network.Response
import com.unicorn.sxmobileoa.business.GongWenParameter

class FwlcFetcher(val type: String, val page: Int) : BaseFetcher<GongWenParameter>() {

    override val busiCode: String = "ydbg_sxYdgbSpdlist"

    override fun initParameters() {
        parameters.apply {
            // 模块名
            put("moduleCode", "OA_FUN_GWGL")
            // 流程名
            put("flowCode", "OA_FLOW_GWGL_SWLC")
            // 1已办 2待办 6所有待办
            put("type", type)
            put("offset", page)
            put("limit", 5)

        }
    }

    override fun map(response: Response): GongWenParameter {
        val json = response.parameters.toString()
        return ComponentHolder.appComponent.getGson()
                .fromJson(json, GongWenParameter::class.java)
    }

}