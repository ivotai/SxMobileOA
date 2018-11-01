package com.unicorn.sxmobileoa.n.attachment

import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.ui.BaseAct
import kotlinx.android.synthetic.main.title_bar.*

class AttachmentAct : BaseAct() {

    override val layoutId = R.layout.act_title_swipe_recycler

    override fun initViews() {
        tvTitle.text = "附件列表"
    }

    override fun bindIntent() {
    }

}
