package com.unicorn.sxmobileoa.detail.ui

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.detail.model.FlowNode

class FlowNodeAdapter:BaseQuickAdapter<FlowNode,BaseViewHolder>(R.layout.item_flow_node){

    override fun convert(helper: BaseViewHolder, item: FlowNode) {
        helper.setText(R.id.tvSpyjNodeName,item.spyjNodeName)
    }

}