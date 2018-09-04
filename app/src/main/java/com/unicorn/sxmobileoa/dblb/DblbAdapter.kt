package com.unicorn.sxmobileoa.dblb

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.support.v4.content.ContextCompat
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.detail.JddbDetailAct

class DblbAdapter:BaseQuickAdapter<Int,BaseViewHolder>(R.layout.item_dblb){

    override fun convert(helper: BaseViewHolder, item: Int) {
        helper.apply {
            setText(R.id.tvBt,"主管办公室领导审批<admin于2017-04-13的接待申请请尽快办理>")

            GradientDrawable().apply {
                setColor(ContextCompat.getColor(mContext,R.color.md_grey_200))
                setStroke(1,ContextCompat.getColor(mContext,R.color.md_grey_300))
            }.let { getView<View>(R.id.root).background = it }

            getView<View>(R.id.root).safeClicks().subscribe { _ -> Intent(mContext,JddbDetailAct::class.java).let { mContext.startActivity(it) } }
        }
    }

}