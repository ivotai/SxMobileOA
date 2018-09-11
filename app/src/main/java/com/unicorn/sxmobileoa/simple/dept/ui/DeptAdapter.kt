package com.unicorn.sxmobileoa.simple.dept.ui

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.g.SelectWrapper
import com.unicorn.sxmobileoa.simple.dept.model.Dept

class DeptAdapter : BaseQuickAdapter<SelectWrapper<Dept>, BaseViewHolder>(R.layout.item_dept) {

    override fun convert(helper: BaseViewHolder, item: SelectWrapper<Dept>) {
        val tvText = helper.getView<TextView>(R.id.tvText)
        tvText.text = item.t.text

        // 选中效果
        tvText.setTextColor(if (item.isSelected) Color.WHITE else Color.BLACK)
        tvText.setBackgroundColor(if (item.isSelected) ContextCompat.getColor(mContext, R.color.colorPrimary) else Color.WHITE)

        // 点击后刷新对应条目
        tvText.safeClicks().subscribe {
            item.isSelected = !item.isSelected
            notifyItemChanged(helper.adapterPosition)
        }
    }

}