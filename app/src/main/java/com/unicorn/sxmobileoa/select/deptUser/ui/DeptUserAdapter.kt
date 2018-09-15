package com.unicorn.sxmobileoa.select.deptUser.ui

import android.arch.lifecycle.LifecycleOwner
import android.widget.TextView
import com.blankj.utilcode.util.ConvertUtils
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.finish
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.select.dept.model.Dept
import com.unicorn.sxmobileoa.select.deptUser.model.DeptUserResult
import com.unicorn.sxmobileoa.select.deptUser.model.User
import com.unicorn.sxmobileoa.select.deptUser.network.DeptUser

class DeptUserAdapter : BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>(null) {

    companion object {
        const val type_dept = 0
        const val type_user = 1
    }

    init {
        addItemType(type_dept, R.layout.item_text)
        addItemType(type_user, R.layout.item_text)
    }

    override fun convert(helper: BaseViewHolder, item: MultiItemEntity) {
        when (item.itemType) {
            type_dept -> {
                item as Dept
                val tvText = helper.getView<TextView>(R.id.tvText)
                tvText.text = item.text

                // 子部门设置 32dp
                val dp16 = ConvertUtils.dp2px(16f)
                val dp32 = dp16 * 2
                val paddingStart = if (item.level0 == 1) dp16 else dp32
                tvText.setPadding(paddingStart, dp16, dp16, dp16)

                // 点击后请求
                tvText.safeClicks().subscribe {
                    if (item.userList != null) {
                        if (item.isExpanded) collapse(helper.adapterPosition)
                        else expand(helper.adapterPosition)
                        return@subscribe
                    }
                    getUser(item)
                }
            }
            type_user -> {
                item as User
                val tvText = helper.getView<TextView>(R.id.tvText)
                tvText.text = item.fullname

                tvText.safeClicks().subscribe {
                    RxBus.get().post(DeptUserResult(listOf(item)))
                    mContext.finish()
                }
            }
        }
    }

    private fun getUser(dept: Dept) {
        DeptUser(dept.id).toMaybe(mContext as LifecycleOwner).subscribe { userList ->
            if (userList.isEmpty()) return@subscribe
            dept.userList = userList
            expand(data.indexOf(dept))
        }
    }


}