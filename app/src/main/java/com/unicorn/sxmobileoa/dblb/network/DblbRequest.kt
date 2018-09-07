package com.unicorn.sxmobileoa.dblb.network

import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.network.model.FydmRequest

class DblbRequest(pageNo: Int) : FydmRequest("fw", Global.court!!.dm) {

    init {
        addParameter("moduleCode", "OA_FUN_GWGL")
        addParameter("flowCode", "OA_FLOW_GWGL_NBFW")
        addParameter("spdCode", "OA_SPD_GWGL_NBFW")
        // 待办
        addParameter("type", "1")
        addParameter("sort", "sysTime")
        addParameter("order", "desc")
        addParameter("start", pageNo.toString())
        addParameter("count", "10")
    }

}
