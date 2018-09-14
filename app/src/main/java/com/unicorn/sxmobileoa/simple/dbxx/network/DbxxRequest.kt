package com.unicorn.sxmobileoa.simple.dbxx.network

import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.network.model.PageRequest
import com.unicorn.sxmobileoa.simple.main.model.Menu

class DbxxRequest(pageNo: Int, menu: Menu) : PageRequest(busiCode = "dbxx", pageNo = pageNo) {

    init {
        addParameter(Key.moduleCode, menu.moduleCode)
        addParameter(Key.flowCode, menu.flowCode)
        addParameter(Key.spdCode, menu.spdCode)
        // 查询待办列表
        addParameter(Key.type, "1")
    }

}
