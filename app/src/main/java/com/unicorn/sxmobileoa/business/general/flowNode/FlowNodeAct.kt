package com.unicorn.sxmobileoa.business.general.flowNode

import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.TestHelper
import com.unicorn.sxmobileoa.app.base.BaseAct
import com.unicorn.sxmobileoa.business.general.CheckedWrapper
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.act_flow_node.*
import java.util.*

class FlowNodeAct : BaseAct() {

    override val layoutId = R.layout.act_flow_node

    private val flowNodeAdapter = FlowNodeAdapter()

    override fun initViews() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            flowNodeAdapter.bindToRecyclerView(this)
        }
        HorizontalDividerItemDecoration.Builder(this)
                .color(ContextCompat.getColor(this, R.color.md_grey_200))
                .size(1)
                .build()
                .let { recyclerView.addItemDecoration(it) }
    }

    override fun bindIntent() {
        TestHelper.getFlowNodeObservable()
//                .delay(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { flowNodeList ->
                    val list = ArrayList<CheckedWrapper<FlowNode>>()
                    flowNodeList.forEach { list.add(CheckedWrapper(it)) }
                    return@map list
                }
                .subscribe { flowNodeAdapter.setNewData(it) }

    }

}
