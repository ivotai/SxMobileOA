package com.unicorn.sxmobileoa.header.sbly

import com.unicorn.sxmobileoa.spd.ui.SpdAct

class SblyAct : SpdAct() {

    override fun addBasicHeaderView() = SblyHeaderView(this, model.menu, model.dbxx, spd).apply {
        flowNodeAdapter.addHeaderView(this)
    }

}