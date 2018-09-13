package com.unicorn.sxmobileoa.header.wbfw

import com.unicorn.sxmobileoa.spd.ui.SpdAct

class WbfwAct : SpdAct() {

    override fun addCustomHeaderView() {
        val headerView = WbfwHeaderView(this,menu , dbxx, spd)
        flowNodeAdapter.addHeaderView(headerView)
    }

}