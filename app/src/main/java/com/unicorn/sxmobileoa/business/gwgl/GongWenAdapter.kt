package com.unicorn.sxmobileoa.business.gwgl

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.business.gwgl.fwlc.Fwlc

class GongWenAdapter : BaseQuickAdapter<Fwlc, BaseViewHolder>(R.layout.item_gong_wen) {

    override fun convert(helper: BaseViewHolder, item: Fwlc) {
        item.apply {
            helper.setText(R.id.tvBt, item.bt)
//            helper.setText(R.id.tvWh, wh)
//            helper.setText(R.id.tvNgr, ngr)
//            helper.setText(R.id.tvNgbm, ngbm)
//            helper.setText(R.id.tvSj, DateTime(sj).toString(Key.DATE_VALUE_FORMAT))
        }

//        helper.getView<View>(R.id.root)
//                .clicks()
//                .subscribe { _ ->
//                    Intent(mContext, ShouWenDetailAct::class.java)
//                            .let { mContext.startActivity(it) }
//                }
    }

}