package com.unicorn.sxmobileoa.business.gwgl.fwlc

import com.unicorn.sxmobileoa.business.general.GeneralAct
import com.unicorn.sxmobileoa.business.general.GeneralPagerAdapter
import com.unicorn.sxmobileoa.business.gwgl.GongWenListFra

class FwlcAct : GeneralAct() {

    override val title = "发文"

    override val adapter = object : GeneralPagerAdapter(supportFragmentManager) {

        override fun getListFra() = GongWenListFra()

    }

}
