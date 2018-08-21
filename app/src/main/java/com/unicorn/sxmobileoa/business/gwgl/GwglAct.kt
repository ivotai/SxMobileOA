package com.unicorn.sxmobileoa.business.gwgl

import com.unicorn.sxmobileoa.business.general.GeneralAct
import com.unicorn.sxmobileoa.business.general.GeneralPagerAdapter
import com.unicorn.sxmobileoa.business.gwgl.list.GongWenListFra

class GwglAct : GeneralAct() {

    override val title = "公文管理"

    override val adapter = object : GeneralPagerAdapter(supportFragmentManager) {

        override fun getListFra() = GongWenListFra()

    }

}
