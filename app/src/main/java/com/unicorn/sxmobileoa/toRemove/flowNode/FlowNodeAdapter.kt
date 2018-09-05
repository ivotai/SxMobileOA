package com.unicorn.sxmobileoa.toRemove.flowNode

import android.widget.RadioButton
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.toRemove.checked.CheckedWrapper
import com.unicorn.sxmobileoa.toRemove.flowNode.model.FlowNode

class FlowNodeAdapter : BaseQuickAdapter<CheckedWrapper<FlowNode>, BaseViewHolder>(R.layout.item_flow_node) {

    override fun convert(helper: BaseViewHolder, item: CheckedWrapper<FlowNode>) {
        val flowNode = item.actual
        val radioButton = helper.getView<RadioButton>(R.id.rbName)
        radioButton.text = flowNode.name
        radioButton.isChecked = item.isChecked

//        radioButton.clicks().subscribe { _ ->
//            data.forEachIndexed { index, checkWrapper ->
//                checkWrapper.isChecked = index == helper.adapterPosition
//            }
//            notifyDataSetChanged()
//        }
    }

}