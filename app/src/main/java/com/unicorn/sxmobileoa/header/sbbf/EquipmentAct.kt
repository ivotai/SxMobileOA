package com.unicorn.sxmobileoa.header.sbbf

import android.support.v7.widget.LinearLayoutManager
import com.blankj.utilcode.util.ConvertUtils
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.header.sbbf.model.Equipment
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import kotlinx.android.synthetic.main.act_title_recycler.*

class EquipmentAct : BaseAct() {

    override val layoutId = R.layout.act_title_recycler

    val mAdapter = EquipmentAdapter()

    override fun initViews() {
        titleBar.setTitle("设备报废详情")
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@EquipmentAct)
            mAdapter.bindToRecyclerView(this)
            HorizontalDividerItemDecoration.Builder(context)
                    .colorResId(R.color.md_grey_100)
                    .size(ConvertUtils.dp2px(10f))
                    .build().let { this.addItemDecoration(it) }
        }
    }

    override fun bindIntent() {
        listOf(1, 2, 3, 4, 5)
                .map { Equipment(spd = Global.spd, position = it) }
                .let { mAdapter.setNewData(it) }
    }

}
