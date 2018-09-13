package com.unicorn.sxmobileoa.spdNext.ui

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.mess.SelectWrapper
import com.unicorn.sxmobileoa.spdNext.model.User

class UserAdapter : BaseQuickAdapter<SelectWrapper<User>, BaseViewHolder>(R.layout.item_text) {

    override fun convert(helper: BaseViewHolder, item: SelectWrapper<User>) {
        helper.setText(R.id.tvText, item.t.userFullName)
    }

}

