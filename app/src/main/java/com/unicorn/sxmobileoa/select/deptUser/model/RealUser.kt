package com.unicorn.sxmobileoa.select.deptUser.model

import com.chad.library.adapter.base.entity.MultiItemEntity
import com.unicorn.sxmobileoa.select.deptUser.ui.DeptUserAdapter

class RealUser() : MultiItemEntity {

    override fun getItemType() = DeptUserAdapter.type_user

}
