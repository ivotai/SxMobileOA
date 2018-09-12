package com.unicorn.sxmobileoa.spd.ui

import android.text.Html
import android.view.View
import android.widget.EditText
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.jakewharton.rxbinding2.widget.RxTextView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.spd.model.FlowNode
import com.unicorn.sxmobileoa.spd.model.Spyj

class FlowNodeAdapter : BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>(null) {

    companion object {
        const val type_flow_node = 0
        const val type_spyj = 1
    }

    init {
        addItemType(type_flow_node, R.layout.item_flow_node)
        addItemType(type_spyj, R.layout.item_spyj)
    }

    override fun convert(helper: BaseViewHolder, item: MultiItemEntity) {
        when (item.itemType) {
            type_flow_node -> {
                item as FlowNode
                helper.setText(R.id.tvSpyjNodeName, item.spyjNodeName)

                helper.getView<View>(R.id.ivArrow).visibility =
                        if (item.spyjList.isEmpty()) View.INVISIBLE
                        else View.VISIBLE

                helper.getView<View>(R.id.root).safeClicks().subscribe { _ ->
                    if (item.isExpanded) collapse(helper.adapterPosition)
                    else expand(helper.adapterPosition)
                }
            }
            type_spyj -> {
                item as Spyj
                helper.setText(R.id.tvSpyjSprName, item.spyjSprName)
                helper.setText(R.id.tvSysTime, item.sysTime)
                helper.setText(R.id.etSpyj, Html.fromHtml(item.spyj))

                val canEdit = item.spyjStatus == 0
                val etSpyj = helper.getView<EditText>(R.id.etSpyj)
                etSpyj.isEnabled = canEdit

                RxTextView.textChanges(etSpyj).map { it.toString() }
                        .subscribe { item.spyj = it }
            }
        }
    }

}