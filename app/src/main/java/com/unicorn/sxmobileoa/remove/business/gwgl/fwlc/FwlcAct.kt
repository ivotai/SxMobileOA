package com.unicorn.sxmobileoa.remove.business.gwgl.fwlc

import android.support.v4.app.Fragment
import com.unicorn.sxmobileoa.remove.business.general.GeneralAct
import com.unicorn.sxmobileoa.remove.business.general.GeneralPagerAdapter

class FwlcAct : GeneralAct() {

    override val title = "发文"

    override val adapter = object : GeneralPagerAdapter(supportFragmentManager) {

        override fun getListFra() = Fragment()
//                GongWenPageFra()

    }

}
