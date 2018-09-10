package com.unicorn.sxmobileoa.detail.ui

import android.annotation.SuppressLint
import android.text.Html
import android.widget.TextView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.detail.model.FlowNode
import com.unicorn.sxmobileoa.detail.model.Spyj

class FlowNodeAdapter : BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>(null) {

    companion object {
        val type_level_0 = 0
        val type_level_1 = 1
    }


    init {
        addItemType(type_level_0, R.layout.item_level_0)
        addItemType(type_level_1, R.layout.item_level_1)
    }
//    override fun convert(helper: BaseViewHolder, item: FlowNode) {
//        val currentNodeId = item.dbxx!!.param.nodeId
//        val result = currentNodeId in item.flowNodeId.split(",")
//        helper.setText(R.id.tvName, item.spyjNodeName + if (result) "可编辑" else "不可编辑")
//    }

    @SuppressLint("SetTextI18n")
    override fun convert(helper: BaseViewHolder, item: MultiItemEntity) {
        when (item.itemType) {
            type_level_0 -> {
                val l0 = item as FlowNode
                helper.setText(R.id.tvName, l0.spyjNodeName)
                helper.setOnClickListener(R.id.tvName) {
                    if (l0.isExpanded) collapse(helper.adapterPosition) else expand(helper.adapterPosition)
                }
            }
            type_level_1 -> {
                val spyj = item as Spyj
                helper.setText(R.id.etName, spyj.spyjSprName + spyj.spyj)
                val etName = helper.getView<TextView>(R.id.etName)
//                etName.text = "${spyj.spyjSprName} - ${spyj.spyj}"
                val html = spyj.spyj

                Html.fromHtml(html).let { etName.text =it }
//                RichText.from(Hteml).singleLoad(false).into(etName);
//                RxTextView/.textChanges(etName).subscribe { spyj.spyj = it.toString() }
            }
        }

    }
}