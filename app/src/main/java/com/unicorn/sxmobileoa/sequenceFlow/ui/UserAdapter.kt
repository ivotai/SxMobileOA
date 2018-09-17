package com.unicorn.sxmobileoa.sequenceFlow.ui

import android.content.Intent
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.mess.model.SelectWrapper
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.select.deptUser.model.User
import com.unicorn.sxmobileoa.select.deptUser.ui.DeptUserAct

class UserAdapter : BaseQuickAdapter<SelectWrapper<User>, BaseViewHolder>(R.layout.item_text) {

    override fun convert(helper: BaseViewHolder, item: SelectWrapper<User>) {
        val tvText = helper.getView<TextView>(R.id.tvText)
        tvText.text = item.t.fullname

        // 选中效果
        tvText.setTextColor(if (item.isSelected) Color.WHITE else Color.BLACK)
        tvText.setBackgroundColor(if (item.isSelected) ContextCompat.getColor(mContext, R.color.colorPrimary) else Color.WHITE)

        // 点击后刷新对应条目
        tvText.safeClicks().subscribe {
            if (item.t.fullname == "选择其他人员"){
                    mContext.startActivity(Intent(mContext, DeptUserAct::class.java).apply {
                        putExtra(Key.type, Key.deptUserResult)
                    })
                return@subscribe
            }

            item.isSelected = !item.isSelected
            notifyItemChanged(helper.adapterPosition)
        }
    }

}

