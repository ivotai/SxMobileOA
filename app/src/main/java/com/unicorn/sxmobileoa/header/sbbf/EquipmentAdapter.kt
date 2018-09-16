package com.unicorn.sxmobileoa.header.sbbf

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.header.sbbf.model.Equipment

class EquipmentAdapter : BaseQuickAdapter<Equipment, BaseViewHolder>(R.layout.item_equipment) {
    override fun convert(helper: BaseViewHolder, item: Equipment) {
        helper.setText(R.id.tvWh, item.bfsbmc)
    }
}
