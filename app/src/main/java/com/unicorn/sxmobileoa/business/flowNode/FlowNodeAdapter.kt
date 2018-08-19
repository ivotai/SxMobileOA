package com.unicorn.sxmobileoa.business.flowNode

import android.widget.RadioButton
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R

class FlowNodeAdapter : BaseQuickAdapter<FlowNode, BaseViewHolder>(R.layout.item_flow_node) {

    override fun convert(helper: BaseViewHolder, item: FlowNode) {
        item.apply {
            helper.getView<RadioButton>(R.id.rbName).text = name
        }
    }

}