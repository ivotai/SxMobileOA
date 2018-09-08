package com.unicorn.sxmobileoa.dblb.ui

import android.content.Intent
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.dblb.model.Dblb
import com.unicorn.sxmobileoa.detail.JddbDetailAct

class DblbAdapter : BaseQuickAdapter<Dblb, BaseViewHolder>(R.layout.item_dblb) {

    override fun convert(helper: BaseViewHolder, item: Dblb) {
        helper.apply {

            setText(R.id.tvBt, item.bt)
            setText(R.id.tvNodeName, item.nodeName)
            setText(R.id.tvWh, "文号：${item.wh}")
            setText(R.id.tvNgrName, "拟稿人：${item.ngrName}")
            // TODO SHIJIAN
            setText(R.id.tvSj, "时间：${"2017-7-25"}")
            setText(R.id.tvNgrDept, "拟稿部门：${item.ngrDept}")

            getView<View>(R.id.root).safeClicks().subscribe { _ -> Intent(mContext, JddbDetailAct::class.java).let { mContext.startActivity(it) } }
        }
    }

}