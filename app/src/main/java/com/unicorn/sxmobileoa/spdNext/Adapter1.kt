package com.unicorn.sxmobileoa.spdNext

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R

class Adapter1 : BaseQuickAdapter<Int, BaseViewHolder>(R.layout.item_court) {
    override fun convert(helper: BaseViewHolder, item: Int) {
        helper.setText(R.id.tvDmms, item.toString())
    }
}

