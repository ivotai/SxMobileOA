package com.unicorn.sxmobileoa.toRemove.flowNode

//import com.unicorn.sxmobileoa.app.clicks
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.toRemove.TestHelper
import com.unicorn.sxmobileoa.app.base.BaseAct
import com.unicorn.sxmobileoa.toRemove.checked.CheckedWrapper
import com.unicorn.sxmobileoa.toRemove.flowNode.model.FlowNode
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
                .color(ContextCompat.getColor(this, R.color.md_grey_300))
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
                .doOnNext { it[0].isChecked = true }
                .subscribe { flowNodeAdapter.setNewData(it) }
//        tvNextStep.clicks().subscribe { startActivity(Intent(this,ApproverSelectionAct::class.java)) }
    }

}
