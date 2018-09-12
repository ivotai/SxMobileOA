package com.unicorn.sxmobileoa.dbxx.network

import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.network.model.PageRequest
import com.unicorn.sxmobileoa.simple.main.model.Menu

class DbxxRequest(pageNo: Int, mainItem: Menu) : PageRequest(busiCode = "dbxx", pageNo = pageNo) {

    init {
        addParameter("moduleCode", mainItem.moduleCode)
        addParameter("flowCode", mainItem.flowCode)
        addParameter("spdCode", mainItem.spdCode)
        // type 1 查询待办列表
        addParameter(Key.type, "1")
    }

}
