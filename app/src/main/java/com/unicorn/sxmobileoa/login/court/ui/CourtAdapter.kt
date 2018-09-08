package com.unicorn.sxmobileoa.login.court.ui

import android.support.v7.app.AppCompatActivity
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.utils.RxBus
import com.unicorn.sxmobileoa.login.court.model.Court

class CourtAdapter : BaseQuickAdapter<Court, BaseViewHolder>(R.layout.item_court) {

    override fun convert(helper: BaseViewHolder, item: Court) {
        helper.setText(R.id.tvDmms, item.dmms)

        val root = helper.getView<View>(R.id.tvDmms)
        root.safeClicks().subscribe { postCourtAndFinish(item) }
    }

    private fun postCourtAndFinish(court: Court) {
        RxBus.get().post(court)
        val activity = mContext as AppCompatActivity
        activity.finish()
    }

}