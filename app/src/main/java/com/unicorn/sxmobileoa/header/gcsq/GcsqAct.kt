package com.unicorn.sxmobileoa.header.gcsq

import com.unicorn.sxmobileoa.spd.ui.SpdAct

class GcsqAct : SpdAct() {

    override fun addBasicHeaderView() = GcsqInfoView(this, model.menu, model.dbxx, spd).apply {
        flowNodeAdapter.addHeaderView(this)
    }

}