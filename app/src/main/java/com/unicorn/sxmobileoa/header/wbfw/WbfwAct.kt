package com.unicorn.sxmobileoa.header.wbfw

import com.unicorn.sxmobileoa.spd.ui.SpdAct

class WbfwAct : SpdAct() {

    override fun addBasicHeaderView() = WbfwInfoView(this, model.menu, spd).apply {
        flowNodeAdapter.addHeaderView(this)
    }

}