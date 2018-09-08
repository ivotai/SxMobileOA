package com.unicorn.sxmobileoa.main.ui

import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.main.model.MainSection

class MainAdapter : BaseSectionQuickAdapter<MainSection, BaseViewHolder>(R.layout.item_main, R.layout.header_main, null) {

    override fun convertHead(helper: BaseViewHolder, item: MainSection) {
        helper.setText(R.id.tvHeader, item.header)
    }

    override fun convert(helper: BaseViewHolder, item: MainSection) {
        helper.setText(R.id.tvName, item.t.text)
    }

}