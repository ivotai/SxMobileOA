package com.unicorn.sxmobileoa.detail.ui

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.utils.RxBus
import com.unicorn.sxmobileoa.detail.SpyjActEvent
import com.unicorn.sxmobileoa.detail.model.FlowNode

class FlowNodeAdapter : BaseQuickAdapter<FlowNode, BaseViewHolder>(R.layout.item_flow_node) {

    override fun convert(helper: BaseViewHolder, item: FlowNode) {
        helper.setText(R.id.tvSpyjNodeName, item.spyjNodeName)

//        if (item.spyjList.isEmpty()) return

//        helper.getView<View>(R.id.ivArrow).visibility = View.VISIBLE
        helper.getView<View>(R.id.root).safeClicks().subscribe {
            // todo qiguai
            RxBus.get().post(SpyjActEvent(helper.adapterPosition - 1))
        }
    }

}