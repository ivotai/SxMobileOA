package com.unicorn.sxmobileoa.spyj

import android.support.v7.widget.LinearLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.ui.BaseFra
import com.unicorn.sxmobileoa.detail.model.FlowNode
import kotlinx.android.synthetic.main.fra_spyj.*

class SpyjFra : BaseFra() {

    override val layoutId = R.layout.fra_spyj

    private lateinit var flowNode: FlowNode

    override fun initArguments() {
        flowNode = arguments!!.getSerializable(Key.flowNode) as FlowNode
    }

    override fun initViews() {
        titleBar.setTitle(flowNode.spyjNodeName)
        initRecyclerView()
    }

    private val spyjAdapter = SpyjAdapter()

    private fun initRecyclerView(){
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            spyjAdapter.bindToRecyclerView(this)
        }
    }

    override fun bindIntent() {
        flowNode.spyjList.let { spyjAdapter.setNewData(it) }
    }

}