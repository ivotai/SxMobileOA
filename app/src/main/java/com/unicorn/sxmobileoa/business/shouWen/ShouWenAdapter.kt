package com.unicorn.sxmobileoa.business.shouWen

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R

class ShouWenAdapter: BaseQuickAdapter<ShouWen, BaseViewHolder>(R.layout.item_shou_wen) {

    override fun convert(helper: BaseViewHolder, item: ShouWen) {
        helper.setText(R.id.tv,item.text)
    }

}