package com.unicorn.sxmobileoa.dbxx.ui

import android.content.Intent
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.detail.ui.DetailAct

class DbxxAdapter(private val moduleCode: String) : BaseQuickAdapter<Dbxx, BaseViewHolder>(R.layout.item_dblb) {

    override fun convert(helper: BaseViewHolder, item: Dbxx) {
        helper.apply {

            setText(R.id.tvBt, item.bt)
            setText(R.id.tvNodeName, item.nodeName)
            setText(R.id.tvWh, "文号：${item.wh}")
            setText(R.id.tvNgrName, "拟稿人：${item.ngrName}")
            // TODO SHIJIAN
            setText(R.id.tvSj, "时间：${"2017-7-25"}")
            setText(R.id.tvNgrDept, "拟稿部门：${item.ngrDept}")

            getView<View>(R.id.root).safeClicks().subscribe { _ ->
                Intent(mContext, DetailAct::class.java).apply {
                    putExtra(Key.moduleCode, moduleCode)
                    putExtra(Key.param, item.param)
                }.let { mContext.startActivity(it) }
            }
        }
    }

}