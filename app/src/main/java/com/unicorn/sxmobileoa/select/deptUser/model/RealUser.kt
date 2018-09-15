package counicom.rn.sxmobileoa.select.deptUser.model

import com.chad.library.adapter.base.entity.MultiItemEntity
import com.unicorn.sxmobileoa.select.deptUser.ui.DeptUserAdapter

data class RealUser(
        val deptOrgCode: String,
        val courtCode: String,
        val courtStdNo: Int,
        val userNo: Int,
        val dept_name: String,
        val uuid: String,
        val deptNo: Int,
        val sortNo: Int,
        val dept_st_name: String,
        val dept_level_code: String,
        val courtNo: Int,
        val id: Int,
        val fullname: String,
        val username: String
) : MultiItemEntity {

    override fun getItemType() = DeptUserAdapter.type_user

}
