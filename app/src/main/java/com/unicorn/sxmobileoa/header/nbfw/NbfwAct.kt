package com.unicorn.sxmobileoa.header.nbfw

import com.unicorn.sxmobileoa.spd.ui.SpdAct

class NbfwAct : SpdAct() {

    override fun addCustomHeaderView() {
        val headerView = NbfwHeaderView(this, dbxx, spd)
        flowNodeAdapter.addHeaderView(headerView)
    }

}