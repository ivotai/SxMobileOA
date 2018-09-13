package com.unicorn.sxmobileoa.header.nbfw

import com.unicorn.sxmobileoa.spd.ui.SpdAct

class NbfwAct : SpdAct() {

    override fun addBasicHeaderView() = NbfwHeaderView(this, menu, dbxx, spd).apply {
        flowNodeAdapter.addHeaderView(this)
    }

}