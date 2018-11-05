package com.unicorn.sxmobileoa.header.nbfw

import com.unicorn.sxmobileoa.spd.ui.SpdAct

class NbfwAct : SpdAct() {

    override fun addBasicHeaderView() = NbfwInfoView(this, model.menu, spd).apply {
        flowNodeAdapter.addHeaderView(this)
    }

}