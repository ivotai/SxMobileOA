package com.unicorn.sxmobileoa.spdNext.ui

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.spdNext.model.NextTaskSequenceFlow

class NextTaskAdapter : BaseQuickAdapter<NextTaskSequenceFlow, BaseViewHolder>(R.layout.item_text) {

    override fun convert(helper: BaseViewHolder, item: NextTaskSequenceFlow) {
        val tvText = helper.getView<TextView>(R.id.tvText)
        tvText.text = item.nextTaskShowName
    }

}

