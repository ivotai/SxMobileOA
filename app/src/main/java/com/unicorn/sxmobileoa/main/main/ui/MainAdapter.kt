package com.unicorn.sxmobileoa.main.main.ui

import android.content.Intent
import android.view.View
import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.main.dbxx.ui.DbxxAct
import com.unicorn.sxmobileoa.main.main.model.Section

class MainAdapter : BaseSectionQuickAdapter<Section, BaseViewHolder>(R.layout.item_main, R.layout.header_main, null) {

    override fun convertHead(helper: BaseViewHolder, item: Section) {
        helper.setText(R.id.tvText, item.header)
    }

    override fun convert(helper: BaseViewHolder, item: Section) {
        item.t.apply {
            helper.setText(R.id.tvText, text)
            helper.setImageResource(R.id.ivImage, resId)
            helper.setText(R.id.tvCount,count.toString())
        }

        helper.getView<View>(R.id.root).safeClicks().subscribe { _ ->
            if (item.t.flowCode == "") return@subscribe
            Intent(mContext, DbxxAct::class.java).apply {
                putExtra(Key.mainItem, item.t)
            }.let { mContext.startActivity(it) }
        }


    }

}