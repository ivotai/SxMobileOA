package com.unicorn.sxmobileoa.dblb.network

import com.unicorn.sxmobileoa.app.network.model.MaybeRequest
import com.unicorn.sxmobileoa.app.ui.page.PageActOrFra

class DblbRequest(pageNo: Int) : MaybeRequest(busiCode = "fw") {

    init {
        addParameter("moduleCode", "OA_FUN_GWGL")
        addParameter("flowCode", "OA_FLOW_GWGL_NBFW")
        addParameter("spdCode", "OA_SPD_GWGL_NBFW")
        // type 1 查询待办列表
        addParameter("type", "1")
        // TODO 移除这两个参数
//        addParameter("sort", "sysTime")
//        addParameter("order", "desc")
        addParameter("start", pageNo.toString())
        addParameter("count", PageActOrFra.rows.toString())
    }

}
