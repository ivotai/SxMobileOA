package com.unicorn.sxmobileoa.n.ggxx.ui

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.n.ggxx.model.Ggxx

class GgxxAdapter : BaseQuickAdapter<Ggxx, BaseViewHolder>(R.layout.item_text) {

    override fun convert(helper: BaseViewHolder, item: Ggxx) {
            helper.setText(R.id.tvText,item.title)
    }

}