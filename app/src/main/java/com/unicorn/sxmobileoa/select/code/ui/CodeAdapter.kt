package com.unicorn.sxmobileoa.select.code.ui

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.finish
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.mess.model.SelectResult
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.select.code.model.Code

class CodeAdapter(val key: String) : BaseQuickAdapter<Code, BaseViewHolder>(R.layout.item_text) {

    override fun convert(helper: BaseViewHolder, item: Code) {
        helper.apply{
            val tvText = getView<TextView>(R.id.tvText)
            tvText.text = item.text

            tvText.safeClicks().subscribe {
                RxBus.get().post(SelectResult(item.text, key))
                mContext.finish()
            }
        }
    }

}