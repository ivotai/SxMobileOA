package com.unicorn.sxmobileoa.header.qjsq

import com.unicorn.sxmobileoa.spd.ui.SpdAct

class QjsqAct : SpdAct() {

    override fun addBasicHeaderView() = QjsqHeaderView(this, model.menu, model.dbxx, spd).apply {
        flowNodeAdapter.addHeaderView(this)
    }

}