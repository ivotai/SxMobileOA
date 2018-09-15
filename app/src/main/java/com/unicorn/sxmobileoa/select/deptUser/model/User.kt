package com.unicorn.sxmobileoa.select.deptUser.model

import com.chad.library.adapter.base.entity.MultiItemEntity
import com.unicorn.sxmobileoa.app.mess.model.Selector
import com.unicorn.sxmobileoa.select.deptUser.ui.DeptUserAdapter

data class User(
        val id: String,
        val fullname: String

) : Selector(), MultiItemEntity {

    override fun getItemType() = DeptUserAdapter.TYPE_USER

}
