package com.unicorn.sxmobileoa.header.ycsq

import com.unicorn.sxmobileoa.spd.ui.SpdAct

class YcsqAct : SpdAct() {

    override fun addBasicHeaderView() = YcsqInfoView(this, model.menu, spd,isCreate).apply {
        flowNodeAdapter.addHeaderView(this)
    }

}