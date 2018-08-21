package com.unicorn.sxmobileoa.business.gwgl

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseFetcher
import com.unicorn.sxmobileoa.app.network.Response
import java.util.*

class GongWenFetcher(val type: String) : BaseFetcher<Any>() {

    override val busiCode: String = "ydbg_sxYdgbSpdlist"

    override val parameters = HashMap<String, Any>().apply {
        put("moduleCode", "OA_FUN_GWGL")
        // 1已办 2待办 6所有待办
        put("type", type)
        //  TODO
        put("flowCode", "")
    }

    override fun map(response: Response): Any {
        val json = response.parameters.toString()
        return ComponentHolder.appComponent.getGson()
                .fromJson(json, Any::class.java)
    }

}