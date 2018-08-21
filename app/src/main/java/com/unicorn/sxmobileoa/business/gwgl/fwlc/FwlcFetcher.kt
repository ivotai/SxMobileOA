package com.unicorn.sxmobileoa.business.gwgl.fwlc

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.Response
import com.unicorn.sxmobileoa.business.gwgl.fetcher.GwglFetcher

class FwlcFetcher( type: String,  page: Int) : GwglFetcher<FwlcParameter>(type,page) {

    override val busiCode: String = "ydbg_sxYdgbSpdlist"

    override fun initParameters() {
        parameters.apply {
            // 流程名
            put("flowCode", "OA_FLOW_GWGL_SWLC")




        }
    }

    override fun map(response: Response): FwlcParameter {
        val json = response.parameters.toString()
        return ComponentHolder.appComponent.getGson()
                .fromJson(json, FwlcParameter::class.java)
    }

}