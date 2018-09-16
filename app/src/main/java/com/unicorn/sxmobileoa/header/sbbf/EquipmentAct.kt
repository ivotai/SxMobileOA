package com.unicorn.sxmobileoa.header.sbbf

import android.support.v7.widget.LinearLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.addDefaultItemDecoration
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.header.sbbf.model.Equipment
import dart.DartModel
import kotlinx.android.synthetic.main.act_title_recycler.*

class EquipmentAct : BaseAct() {

    override val layoutId = R.layout.act_title_recycler

    @DartModel
    lateinit var model: EquipmentActNavigationModel

    val mAdapter = EquipmentAdapter()

    override fun initViews() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@EquipmentAct)
            mAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration()
        }
    }

    override fun bindIntent() {
        listOf(1, 2, 3, 4, 5)
                .map { Equipment(spd = model.spd, position = it) }
                .let { mAdapter.setNewData(it) }
    }

}
