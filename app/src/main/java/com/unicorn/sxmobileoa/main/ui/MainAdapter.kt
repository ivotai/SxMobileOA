package com.unicorn.sxmobileoa.main.ui

import android.content.Intent
import android.view.View
import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.dblb.ui.DblbAct
import com.unicorn.sxmobileoa.main.model.MainSection

class MainAdapter : BaseSectionQuickAdapter<MainSection, BaseViewHolder>(R.layout.item_main, R.layout.header_main, null) {

    override fun convertHead(helper: BaseViewHolder, item: MainSection) {
        helper.setText(R.id.tvText, item.header)
    }

    override fun convert(helper: BaseViewHolder, item: MainSection) {
        item.t.apply {
            helper.setText(R.id.tvText, text)
            helper.setImageResource(R.id.ivImage, resId)
        }

        helper.getView<View>(R.id.root).safeClicks().subscribe { _ ->
            if (item.t.flowCode == "") return@subscribe
            Intent(mContext, DblbAct::class.java).apply {
                putExtra(Key.mainItem, item)
            }.let { mContext.startActivity(it) }
        }
    }

}