package com.unicorn.sxmobileoa.business.flowNode

import android.widget.RadioButton
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.clicks

class FlowNodeAdapter : BaseQuickAdapter<CheckWrapper<FlowNode>, BaseViewHolder>(R.layout.item_flow_node) {

    override fun convert(helper: BaseViewHolder, item: CheckWrapper<FlowNode>) {
        val flowNode = item.t
        val radioButton = helper.getView<RadioButton>(R.id.rbName)
        radioButton.text = flowNode.name
        radioButton.isChecked = item.isChecked

        radioButton.clicks().subscribe { _ ->
            data.forEachIndexed { index, checkWrapper ->
                checkWrapper.isChecked = index == helper.adapterPosition
            }
            notifyDataSetChanged()
        }
    }

}