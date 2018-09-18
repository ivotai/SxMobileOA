package com.unicorn.sxmobileoa.header.wply

import android.support.v7.widget.LinearLayoutManager
import com.blankj.utilcode.util.ConvertUtils
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.header.wply.model.Wply
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import kotlinx.android.synthetic.main.act_title_recycler.*

class WplyDetailAct : BaseAct() {

    override val layoutId = R.layout.act_title_recycler

    val mAdapter = WplyAdapter()

    override fun initViews() {
        titleBar.setTitle("物品领用详情")
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@WplyDetailAct)
            mAdapter.bindToRecyclerView(this)
            HorizontalDividerItemDecoration.Builder(context)
                    .colorResId(R.color.md_grey_100)
                    .size(ConvertUtils.dp2px(10f))
                    .build().let { this.addItemDecoration(it) }
        }
    }

    override fun bindIntent() {
        listOf(1, 2, 3, 4, 5)
                .map { Wply(spd = Global.spd, position = it) }
                .let { mAdapter.setNewData(it) }
    }

}
