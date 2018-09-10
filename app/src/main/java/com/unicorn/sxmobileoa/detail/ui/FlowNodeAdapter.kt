package com.unicorn.sxmobileoa.detail.ui

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.detail.model.FlowNode

class FlowNodeAdapter : BaseQuickAdapter<FlowNode, BaseViewHolder>(R.layout.item_flow_node) {

    override fun convert(helper: BaseViewHolder, item: FlowNode) {
        val currentNodeId = item.dbxx!!.param.nodeId
        val result = currentNodeId in item.flowNodeId.split(",")
        helper.setText(R.id.tvName, item.spyjNodeName + if (result) "可编辑" else "不可编辑")
    }

}