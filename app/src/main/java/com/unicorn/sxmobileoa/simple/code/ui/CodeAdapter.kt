package com.unicorn.sxmobileoa.simple.code.ui

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.simple.code.model.Code
import com.unicorn.sxmobileoa.simple.code.model.CodeResult

class CodeAdapter(val key: String) : BaseQuickAdapter<Code, BaseViewHolder>(R.layout.item_text) {

    override fun convert(helper: BaseViewHolder, item: Code) {
        with(helper) {
            setText(R.id.tvText, "")

            getView<View>(R.id.tvText).safeClicks().subscribe {
                RxBus.get().post(CodeResult(item, key))
                (mContext as BaseAct).finish()
            }
        }
    }

}