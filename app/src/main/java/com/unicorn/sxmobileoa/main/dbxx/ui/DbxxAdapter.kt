package com.unicorn.sxmobileoa.main.dbxx.ui

import android.content.Intent
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.detail.ui.DetailAct
import com.unicorn.sxmobileoa.main.dbxx.model.Dbxx

class DbxxAdapter() : BaseQuickAdapter<Dbxx, BaseViewHolder>(R.layout.item_dbxx) {

    override fun convert(helper: BaseViewHolder, item: Dbxx) {
        helper.apply {

            setText(R.id.tvBt, item.bt)
            setText(R.id.tvNodeName, item.nodeName)
            setText(R.id.tvWh, "文号：${item.wh}")
            setText(R.id.tvNgrName, "拟稿人：${item.ngrName}")
            setText(R.id.tvCjrq, "时间：${item.cjrq}")
            setText(R.id.tvNgrDept, "拟稿部门：${item.ngrDept}")

            getView<View>(R.id.root).safeClicks().subscribe { _ ->
                Intent(mContext, DetailAct::class.java).apply {
                    putExtra(Key.dbxx, item)
                }.let { mContext.startActivity(it) }
            }
        }
    }

}