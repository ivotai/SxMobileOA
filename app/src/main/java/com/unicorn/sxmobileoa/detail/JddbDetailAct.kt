package com.unicorn.sxmobileoa.detail

import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.base.BaseAct

class JddbDetailAct : BaseAct() {

    override val layoutId = R.layout.act_jddb_detail

    override fun initViews() {
    }

    override fun bindIntent() {
        listOf(
                Advice("部门领导意见"),
                Advice("主管办公室领导意见")
        ).forEach {  }

//        val container = LinearLayout(this)
    }

//    private fun addAdvice(container:LinearLayout,advice:Advice){
//        val linearLayout = LinearLayout(this)
//        val mlp = ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.MATCH_PARENT,ConvertUtils.dp2px(80f))
//
//    }

}
