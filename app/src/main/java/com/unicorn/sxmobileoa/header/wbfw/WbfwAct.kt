package com.unicorn.sxmobileoa.header.wbfw

import com.unicorn.sxmobileoa.spd.ui.SpdAct

class WbfwAct : SpdAct() {

    override fun addBasicHeaderView() = WbfwHeaderView(this, model.menu, model.dbxx, spd).apply {
        flowNodeAdapter.addHeaderView(this)
    }

}