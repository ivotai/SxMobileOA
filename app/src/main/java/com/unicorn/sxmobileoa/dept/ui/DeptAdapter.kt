package com.unicorn.sxmobileoa.dept.ui

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.widget.TextView
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.dept.model.Dept
import com.unicorn.sxmobileoa.g.SelectWrapper

class DeptAdapter : BaseQuickAdapter<SelectWrapper<Dept>, BaseViewHolder>(R.layout.item_dept) {

    override fun convert(helper: BaseViewHolder, item: SelectWrapper<Dept>) {
        val tvText = helper.getView<TextView>(R.id.tvText)
        tvText.text = item.t.text

        // 选中效果
        tvText.setBackgroundColor(if (item.isSelected) ContextCompat.getColor(mContext, R.color.colorPrimary) else Color.WHITE)
        tvText.setTextColor(if (item.isSelected) Color.WHITE else Color.BLACK)

        // 点击后刷新对应条目
        tvText.safeClicks().subscribe {
            item.isSelected = !item.isSelected
            notifyItemChanged(helper.adapterPosition)
            ToastUtils.showShort(helper.adapterPosition.toString())
        }
    }

}