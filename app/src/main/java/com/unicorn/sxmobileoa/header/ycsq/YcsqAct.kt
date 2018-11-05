package com.unicorn.sxmobileoa.header.ycsq

import com.unicorn.sxmobileoa.spd.ui.SpdAct

class YcsqAct : SpdAct() {

    override fun addBasicHeaderView() = YcsqInfoView(this, model.menu, spd).apply {
        flowNodeAdapter.addHeaderView(this)
    }

}