package com.unicorn.sxmobileoa.select.dept.ui

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.widget.TextView
import com.blankj.utilcode.util.ConvertUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.mess.model.SelectWrapper
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.select.dept.model.Dept

class DeptAdapter : BaseQuickAdapter<SelectWrapper<Dept>, BaseViewHolder>(R.layout.item_text) {

    override fun convert(helper: BaseViewHolder, item: SelectWrapper<Dept>) {
        val tvText = helper.getView<TextView>(R.id.tvText)
        tvText.text = item.t.text

        // 子部门设置 32dp
        val dp16 = ConvertUtils.dp2px(16f)
        val dp32 = dp16 * 2
        val paddingStart = if (item.t.level0 == 1) dp16 else dp32
        tvText.setPadding(paddingStart, dp16, dp16, dp16)

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