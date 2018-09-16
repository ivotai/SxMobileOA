package com.unicorn.sxmobileoa.header.sbbf

import android.support.v7.widget.LinearLayoutManager
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.header.sbbf.model.Equipment
import com.unicorn.sxmobileoa.header.sbbf.model.EquipmentActNavigationModel
import com.unicorn.sxmobileoa.spd.network.saveSpd.SaveSpd
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import dart.DartModel
import kotlinx.android.synthetic.main.act_title_recycler.*

class EquipmentAct : BaseAct() {

    override val layoutId = R.layout.act_title_recycler

    @DartModel
    lateinit var model: EquipmentActNavigationModel

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

        titleBar.setOperation("保存").safeClicks().subscribe {
            SaveSpd(model.spd).toMaybe(this@EquipmentAct).subscribe { _ ->
                ToastUtils.showShort("保存成功")
            }
        }
    }

    override fun bindIntent() {
        listOf(1, 2, 3, 4, 5)
                .map { Equipment(spd = model.spd, position = it) }
                .let { mAdapter.setNewData(it) }
    }

}
