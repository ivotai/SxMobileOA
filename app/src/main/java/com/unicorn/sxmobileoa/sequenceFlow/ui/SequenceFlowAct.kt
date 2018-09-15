package com.unicorn.sxmobileoa.sequenceFlow.ui

import android.support.v7.widget.LinearLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.addDefaultItemDecoration
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.mess.model.SelectWrapper
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.sequenceFlow.model.NextTaskSequenceFlow
import com.unicorn.sxmobileoa.sequenceFlow.model.SequenceFlowActNavigationModel
import com.unicorn.sxmobileoa.sequenceFlow.model.SequenceFlowResult
import com.unicorn.sxmobileoa.sequenceFlow.network.nextUser.NextUser
import com.unicorn.sxmobileoa.sequenceFlow.network.spdNext.SpdNext
import dart.DartModel
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.act_spd_next.*

class SequenceFlowAct : BaseAct() {

    private val sequenceFlowAdapter = SequenceFlowAdapter()

    private val userAdapter = UserAdapter()


    override fun initViews() {
        titleBar.setTitle("选择流程节点及相关人员")
        titleBar.setOperation("确认").safeClicks().subscribe { next() }
        rvSequenceFlow.apply {
            layoutManager = LinearLayoutManager(this@SequenceFlowAct)
            sequenceFlowAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration()
        }
        rvUser.apply {
            layoutManager = LinearLayoutManager(this@SequenceFlowAct)
            userAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration()
        }
    }


    private fun next() {
        val list = sequenceFlowAdapter.data.filter { it.isSelected }.map { it.t }
        if (list.isEmpty()) return

        val list2 = userAdapter.data.filter { it.isSelected }.map { it.t }
        if (list2.isEmpty()) return

        val result = SequenceFlowResult(list[0], list2)
        RxBus.get().post(result)
        finish()
    }

    override fun bindIntent() {
        getSptNext()
    }

    private fun getSptNext() {
        SpdNext(model.menu, model.dbxx, model.spd).toMaybe(this)
                .map { it.nextTask_sequenceFlow }
                .map { it.map { sequenceFlow -> SelectWrapper(sequenceFlow) } }
                .subscribe { sequenceFlowAdapter.setNewData(it) }
    }


    override fun registerEvent() {
        // TODO dealperson  == 1  时  结束节点无需选择人员
        RxBus.get().registerEvent(NextTaskSequenceFlow::class.java, this, Consumer {
            NextUser(model.spd, it).toMaybe(this@SequenceFlowAct).subscribe { list ->
                userAdapter.setNewData(list.map { flowUser -> SelectWrapper(flowUser) })
            }
        })
    }


    override val layoutId = R.layout.act_spd_next

    @DartModel
    lateinit var model: SequenceFlowActNavigationModel


}