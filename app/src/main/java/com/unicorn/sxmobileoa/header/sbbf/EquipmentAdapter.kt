package com.unicorn.sxmobileoa.header.sbbf

import android.widget.TextView
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.clickDate
import com.unicorn.sxmobileoa.app.get
import com.unicorn.sxmobileoa.app.textChanges
import com.unicorn.sxmobileoa.header.sbbf.model.Equipment

class EquipmentAdapter : BaseQuickAdapter<Equipment, BaseViewHolder>(R.layout.item_equipment) {

    override fun convert(helper: BaseViewHolder, item: Equipment) {
        helper.setText(R.id.tvBfsbmc, item.bfsbmc)
        helper.setText(R.id.tvPpjxh, item.ppjxh)
        helper.setText(R.id.tvSl, item.sl)

        val tvBfrq = helper.getView<TextView>(R.id.tvBfrq)
        tvBfrq.textChanges().subscribe { item.bfrq = it
        ToastUtils.showShort(item.spd.get("bfrq1_date"))
        }
        tvBfrq.clickDate()
    }

}
