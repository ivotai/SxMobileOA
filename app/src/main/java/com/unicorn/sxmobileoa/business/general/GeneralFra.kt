package com.unicorn.sxmobileoa.business.general

import android.support.v4.content.ContextCompat
import com.blankj.utilcode.util.ConvertUtils
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.base.ListFra
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration

abstract class GeneralFra<MODEL> : ListFra<MODEL>() {

    override fun initViews() {
        super.initViews()
        addItemDecoration()
    }

    override fun bindIntent() {
        // do nothing
    }

    private fun addItemDecoration() {
        // TODO 自定义 ItemDecoration
        HorizontalDividerItemDecoration.Builder(context)
                .color(ContextCompat.getColor(context!!, R.color.md_grey_100))
                .size(ConvertUtils.dp2px(16f))
                .showLastDivider()
                .build()
                .let { recyclerView1.addItemDecoration(it) }
    }

}