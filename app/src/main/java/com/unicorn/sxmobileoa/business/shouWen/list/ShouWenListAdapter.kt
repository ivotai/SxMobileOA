package com.unicorn.sxmobileoa.business.shouWen.list

import android.content.Intent
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.chongqing.Key
import com.unicorn.sxmobileoa.app.clicks
import com.unicorn.sxmobileoa.business.flowNode.FlowNodeAct
import com.unicorn.sxmobileoa.business.shouWen.ShouWen
import org.joda.time.DateTime

class ShouWenListAdapter : BaseQuickAdapter<ShouWen, BaseViewHolder>(R.layout.item_shou_wen) {

    override fun convert(helper: BaseViewHolder, item: ShouWen) {
        item.apply {
            helper.setText(R.id.tvBt, bt)
            helper.setText(R.id.tvWh, wh)
            helper.setText(R.id.tvNgr, ngr)
            helper.setText(R.id.tvNgbm, ngbm)
            helper.setText(R.id.tvSj, DateTime(sj).toString(Key.DATE_VALUE_FORMAT))
        }

        helper.getView<View>(R.id.tvBt)
                .clicks()
                .subscribe { _ ->
                    Intent(mContext, FlowNodeAct::class.java)
                            .let { mContext.startActivity(it) }
                }
    }

}