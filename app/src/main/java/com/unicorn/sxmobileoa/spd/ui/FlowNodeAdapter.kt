package com.unicorn.sxmobileoa.spd.ui

import android.annotation.SuppressLint
import android.text.Html
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.orhanobut.logger.Logger
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.mess.MyHolder
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.textChanges2
import com.unicorn.sxmobileoa.spd.model.FlowNode
import com.unicorn.sxmobileoa.spd.model.Spyj
import kotlinx.android.synthetic.main.item_spyj.*

class FlowNodeAdapter : BaseMultiItemQuickAdapter<MultiItemEntity, MyHolder>(null) {

    companion object {
        const val TYPE_FLOW_NODE = 0
        const val TYPE_SPYJ = 1
    }

    init {
        addItemType(TYPE_FLOW_NODE, R.layout.item_flow_node)
        addItemType(TYPE_SPYJ, R.layout.item_spyj)
    }

    override fun convert(helper: MyHolder, item: MultiItemEntity) {
        when (item.itemType) {
            TYPE_FLOW_NODE -> {
                item as FlowNode
                helper.setText(R.id.tvSpyjNodeName, item.safeSpyjNodeName)

                helper.getView<View>(R.id.ivArrow).visibility =
                        if (item.spyjList.isEmpty()) View.INVISIBLE
                        else View.VISIBLE

                helper.getView<View>(R.id.root).safeClicks().subscribe { _ ->
                    if (item.subItems.isEmpty()) return@subscribe
                    if (item.isExpanded) collapse(helper.adapterPosition)
                    else expand(helper.adapterPosition)
                }
            }
            TYPE_SPYJ -> {
                item as Spyj
                helper.setText(R.id.tvSpyjSprName, item.spyjSprName)
                helper.setText(R.id.tvSysTime, item.sysTime)
                helper.setText(R.id.etSpyj, Html.fromHtml(item.spyj))

                val canEdit = item.spyjStatus == 0 && item.createUserId == Global.loginInfo!!.userId
                val etSpyj = helper.getView<EditText>(R.id.etSpyj)
                etSpyj.isEnabled = canEdit
            }
        }
    }

    @SuppressLint("CheckResult")
    override fun onCreateDefViewHolder(parent: ViewGroup?, viewType: Int): MyHolder {
        val helper = super.onCreateDefViewHolder(parent, viewType)
        if (viewType == TYPE_SPYJ) {
            helper.etSpyj.textChanges2().filter { it.isNotEmpty() }.subscribe {
                val adapterPos = helper.adapterPosition
                if (adapterPos == -1) return@subscribe
                val index = adapterPos - 1
                val target = mData[index]
                if (target is Spyj) {
                    target.spyj = it
                }
                Logger.e(index.toString())
            }
        }
        return helper
    }


}