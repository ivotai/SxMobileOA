package com.unicorn.sxmobileoa.dblb

import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.base.BaseAct

class DblbAct : BaseAct() {

    override val layoutId = R.layout.act_dblb

    override fun initViews() {
    }

    override fun bindIntent() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.root, DblbFra())
        transaction.commit()
    }

}
