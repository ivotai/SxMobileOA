package com.unicorn.sxmobileoa.spdNext.ui

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.mess.SelectWrapper
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.spdNext.model.NextTaskSequenceFlow

class NextTaskSequenceFlowAdapter : BaseQuickAdapter<SelectWrapper<NextTaskSequenceFlow>, BaseViewHolder>(R.layout.item_text) {

    override fun convert(helper: BaseViewHolder, item: SelectWrapper<NextTaskSequenceFlow>) {
        val tvText = helper.getView<TextView>(R.id.tvText)
        tvText.text = item.t.nextTaskShowName

        // 选中效果
        tvText.setTextColor(if (item.isSelected) Color.WHITE else Color.BLACK)
        tvText.setBackgroundColor(if (item.isSelected) ContextCompat.getColor(mContext, R.color.colorPrimary) else Color.WHITE)

        // 点击变动所有item
        tvText.safeClicks().subscribe {
            RxBus.get().post(item.t)

            if (item.isSelected) return@subscribe
            data.forEach { wrapper -> wrapper.isSelected = wrapper == item }
            notifyDataSetChanged()
        }
    }

}

