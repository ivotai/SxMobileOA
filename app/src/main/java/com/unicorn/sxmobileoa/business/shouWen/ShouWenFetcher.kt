package com.unicorn.sxmobileoa.business.shouWen

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseFetcher
import com.unicorn.sxmobileoa.app.network.Response
import java.util.*

class ShouWenFetcher: BaseFetcher<Any>() {

    override val busiCode: String = "ydbg_sxYdgbSpdlist"

    override val parameters = HashMap<String, Any>().apply {
        put("moduleCode", "OA_FUN_GWGL_WBFW")
        put("type", "6")
    }

    override fun map(response: Response): Any {
        val json = response.parameters.toString()
        return ComponentHolder.appComponent.getGson()
                .fromJson(json, Any::class.java)
    }

}