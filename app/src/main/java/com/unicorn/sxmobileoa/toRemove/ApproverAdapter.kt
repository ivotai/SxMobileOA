package com.unicorn.sxmobileoa.toRemove

import android.widget.CheckBox
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.toRemove.business.approver.model.Approver
import com.unicorn.sxmobileoa.toRemove.checked.CheckedWrapper

class ApproverAdapter : BaseQuickAdapter<CheckedWrapper<Approver>, BaseViewHolder>(R.layout.item_approver_selection) {

    override fun convert(helper: BaseViewHolder, item: CheckedWrapper<Approver>) {
        helper.setText(R.id.tvName, item.actual.name)

        val checkBox = helper.getView<CheckBox>(R.id.checkBox)
//        helper.getView<View>(R.id.root).clicks().subscribe { _ ->
//            val result = !checkBox.isChecked
//            checkBox.isChecked = result
//            item.isChecked = result
//
//            com.orhanobut.logger.Logger.e(data.toString())
//        }
    }

}