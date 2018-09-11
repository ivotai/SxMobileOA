package com.unicorn.sxmobileoa.dept.ui

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.dept.model.Dept
import com.unicorn.sxmobileoa.dept.model.SelectWrapper

class DeptAdapter : BaseQuickAdapter<SelectWrapper<Dept>, BaseViewHolder>(R.layout.item_dept) {

    override fun convert(helper: BaseViewHolder, item: SelectWrapper<Dept>) {
        val root = helper.getView<TextView>(R.id.root)
        root.text = item.t.text
        root.setBackgroundColor(if(item.isSelected) ContextCompat.getColor(mContext,R.color.colorPrimary) else Color.WHITE)
        root.setTextColor(if(item.isSelected) Color.WHITE else Color.BLACK)


        // 点击后刷新对应条目
        root.safeClicks().subscribe {
            item.isSelected = !item.isSelected
            notifyItemChanged(helper.adapterPosition)
        }
    }

}