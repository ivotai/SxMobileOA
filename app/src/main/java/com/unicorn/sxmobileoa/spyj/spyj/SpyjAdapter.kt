package com.unicorn.sxmobileoa.spyj.spyj

import android.widget.EditText
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jakewharton.rxbinding2.widget.RxTextView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.spd.model.Spyj

class SpyjAdapter : BaseQuickAdapter<Spyj, BaseViewHolder>(R.layout.item_spyj) {

    override fun convert(helper: BaseViewHolder, item: Spyj) {
        helper.setText(R.id.tvSpyjSprName, item.spyjSprName)
        helper.setText(R.id.tvSysTime, item.sysTime)
        helper.setText(R.id.etSpyj, item.spyj)

        val enable = item.spyjStatus == 0
        val etSpyj = helper.getView<EditText>(R.id.etSpyj)
        etSpyj.isEnabled = enable

        RxTextView.textChanges(etSpyj).map { it.toString() }
                .subscribe { item.spyj = it }
    }

}