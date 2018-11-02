package com.unicorn.sxmobileoa.n.attachment

import android.arch.lifecycle.AndroidViewModel
import android.support.v7.widget.LinearLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.addDefaultItemDecoration
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.select.code.model.CodeActNavigationModel
import com.unicorn.sxmobileoa.select.code.ui.CodeAdapter
import dart.DartModel
import kotlinx.android.synthetic.main.act_title_recycler.*
import kotlinx.android.synthetic.main.title_bar.*

class AttachmentAct : BaseAct() {

    override val layoutId = R.layout.act_title_recycler

    override fun initViews() {
        tvTitle.text = "附件"
        initRecyclerView()
    }

    private  val mAdapter = AttachmentAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            mAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration()
        }
    }

    override fun bindIntent() {
        model.spd.spdFj.let { mAdapter.setNewData(it) }
    }

    @DartModel
    lateinit var model: AttachmentActNavigationModel

}

