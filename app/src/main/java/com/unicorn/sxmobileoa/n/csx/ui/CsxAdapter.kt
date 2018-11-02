package com.unicorn.sxmobileoa.n.csx.ui

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.n.csx.model.Csx
import com.unicorn.sxmobileoa.n.ggxx.model.Ggxx

class CsxAdapter : BaseQuickAdapter<Csx, BaseViewHolder>(R.layout.item_text) {

    override fun convert(helper: BaseViewHolder, item: Csx) {
            helper.setText(R.id.tvText,item.ahqc)
    }

}