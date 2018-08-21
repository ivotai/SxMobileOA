package com.unicorn.sxmobileoa.business.gouWen

import com.unicorn.sxmobileoa.business.general.GeneralAct
import com.unicorn.sxmobileoa.business.general.GeneralPagerAdapter

class GongWenAct : GeneralAct() {

    override val title = "公文管理"

    override val adapter = object : GeneralPagerAdapter(supportFragmentManager) {

        override fun getListFra() = GongWenListFra()

    }

}
