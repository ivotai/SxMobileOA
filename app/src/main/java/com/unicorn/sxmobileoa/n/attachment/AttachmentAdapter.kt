package com.unicorn.sxmobileoa.n.attachment

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.finish
import com.unicorn.sxmobileoa.app.mess.MyHolder
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.mess.model.TextResult
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.select.code.model.Code
import com.unicorn.sxmobileoa.spd.model.Fj
import kotlinx.android.synthetic.main.item_text.*

class AttachmentAdapter() : BaseQuickAdapter<Fj, MyHolder>(R.layout.item_text) {

    override fun convert(helper: MyHolder, item: Fj) {
        helper.apply {
            tvText.text = item.fjmc

            tvText.safeClicks().subscribe {
            }
        }
    }

}