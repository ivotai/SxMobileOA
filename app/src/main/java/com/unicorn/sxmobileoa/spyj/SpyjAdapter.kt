package com.unicorn.sxmobileoa.spyj

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.detail.model.Spyj

class SpyjAdapter : BaseQuickAdapter<Spyj, BaseViewHolder>(R.layout.item_spyj) {

    override fun convert(helper: BaseViewHolder, item: Spyj) {
        helper.setText(R.id.tvSpyjSprName, "${item.spyjSprName}")
        helper.setText(R.id.tvSysTime, item.sysTime)
        helper.setText(R.id.tvSpyj, item.spyj)
    }

}