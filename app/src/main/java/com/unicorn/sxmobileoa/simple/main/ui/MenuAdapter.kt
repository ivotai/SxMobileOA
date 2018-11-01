package com.unicorn.sxmobileoa.simple.main.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.n.ggxx.ui.GgxxAct
import com.unicorn.sxmobileoa.simple.dbxx.ui.DbxxAct
import com.unicorn.sxmobileoa.simple.main.model.section.MenuSection

@SuppressLint("CheckResult")
class MenuAdapter : BaseSectionQuickAdapter<MenuSection, BaseViewHolder>(R.layout.item_menu, R.layout.header_menu, null) {

    override fun convertHead(helper: BaseViewHolder, item: MenuSection) {
        helper.setText(R.id.tvText, item.header)
    }

    override fun convert(helper: BaseViewHolder, item: MenuSection) {
        item.t.apply {
            helper.setText(R.id.tvText, text)
            helper.setText(R.id.tvCount, count.toString())
            helper.setImageResource(R.id.ivImage, resId)
        }
        if (item.t.count != 0) {
            helper.getView<View>(R.id.root).safeClicks().subscribe { _ ->
                if (item.t.text == "消息公告") {
                    mContext.startActivity(Intent(mContext, GgxxAct::class.java))
                    return@subscribe
                }

                mContext.startActivity(Intent(mContext, DbxxAct::class.java).apply {
                    putExtra(Key.menu, item.t)
                })
            }
        }
        helper.getView<View>(R.id.flCount).visibility = if (item.t.count == 0) View.INVISIBLE else View.VISIBLE
    }

}