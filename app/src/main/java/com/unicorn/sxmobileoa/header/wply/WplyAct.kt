package com.unicorn.sxmobileoa.header.wply

import com.unicorn.sxmobileoa.spd.ui.SpdAct

class WplyAct : SpdAct() {

    override fun addBasicHeaderView() = WplyInfoView(this, model.menu, model.dbxx, spd).apply {
        flowNodeAdapter.addHeaderView(this)
    }

}