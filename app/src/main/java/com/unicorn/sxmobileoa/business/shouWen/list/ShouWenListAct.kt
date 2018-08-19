package com.unicorn.sxmobileoa.business.shouWen.list

import com.unicorn.sxmobileoa.business.general.GeneralAct
import com.unicorn.sxmobileoa.business.general.GeneralPagerAdapter

class ShouWenListAct : GeneralAct() {

    override val title = "收文"

    override val adapter = object : GeneralPagerAdapter(supportFragmentManager) {

        override fun getItem(position: Int) = ShouWenListFra()

    }

}
