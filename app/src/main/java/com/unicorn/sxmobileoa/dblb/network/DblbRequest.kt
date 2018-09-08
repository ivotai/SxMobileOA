package com.unicorn.sxmobileoa.dblb.network

import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.network.model.PageRequest
import com.unicorn.sxmobileoa.main.model.MainItem

class DblbRequest(pageNo: Int, mainItem: MainItem) : PageRequest(busiCode = "fw", pageNo = pageNo) {

    init {
        addParameter("moduleCode", mainItem.moduleCode)
        addParameter("flowCode", mainItem.flowCode)
        addParameter("spdCode", mainItem.spdCode)
        // type 1 查询待办列表
        addParameter(Key.type, "1")
    }

}
