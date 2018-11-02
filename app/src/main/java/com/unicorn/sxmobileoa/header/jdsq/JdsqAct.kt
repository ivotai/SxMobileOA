package com.unicorn.sxmobileoa.header.jdsq

import com.unicorn.sxmobileoa.spd.ui.SpdAct

class JdsqAct : SpdAct() {

    override fun addBasicHeaderView() = JdsqInfoView(this, model.menu, model.dbxx, spd).apply {
        flowNodeAdapter.addHeaderView(this)
    }

}