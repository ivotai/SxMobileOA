package com.unicorn.sxmobileoa.court.ui

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.union.RxBus
import com.unicorn.sxmobileoa.court.event.CourtSelectEvent
import com.unicorn.sxmobileoa.court.model.Court

class CourtAdapter : BaseQuickAdapter<Court, BaseViewHolder>(R.layout.item_court) {

    override fun convert(helper: BaseViewHolder, item: Court) {
        helper.setText(R.id.tvDmms, item.dmms)

        val root = helper.getView<View>(R.id.tvDmms)
        root.safeClicks().subscribe { postCourtSelectEvent(item) }
    }

    private fun postCourtSelectEvent(court: Court) {
        RxBus.get().post(CourtSelectEvent(court))
    }

}