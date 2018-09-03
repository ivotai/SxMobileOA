package com.unicorn.sxmobileoa.remove.business.gwgl.fwlc

import com.unicorn.sxmobileoa.remove.business.general.GeneralAct
import com.unicorn.sxmobileoa.remove.business.general.GeneralPagerAdapter
import com.unicorn.sxmobileoa.remove.business.gwgl.GongWenListFra

class FwlcAct : GeneralAct() {

    override val title = "发文"

    override val adapter = object : GeneralPagerAdapter(supportFragmentManager) {

        override fun getListFra() = GongWenListFra()

    }

}
