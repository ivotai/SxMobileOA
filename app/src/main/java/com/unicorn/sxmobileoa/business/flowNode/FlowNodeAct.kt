package com.unicorn.sxmobileoa.business.flowNode

import android.support.v7.widget.LinearLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.TestHelper
import com.unicorn.sxmobileoa.app.base.BaseAct
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.act_flow_node.*
import java.util.*
import java.util.concurrent.TimeUnit

class FlowNodeAct : BaseAct() {

    override val layoutId = R.layout.act_flow_node

    private val flowNodeAdapter = FlowNodeAdapter()

    override fun initViews() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            flowNodeAdapter.bindToRecyclerView(this)
        }
    }

    override fun bindIntent() {
        TestHelper.getFlowNodeObservable()
                .delay(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { flowNodeList ->
                    val list = ArrayList<CheckWrapper<FlowNode>>()
                    flowNodeList.forEach { list.add(CheckWrapper(it)) }
                    return@map list
                }
                .subscribe { flowNodeAdapter.setNewData(it) }
    }

}
