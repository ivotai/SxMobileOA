package com.unicorn.sxmobileoa.toRemove

import com.unicorn.sxmobileoa.toRemove.flowNode.model.FlowNode
import io.reactivex.Observable
import java.util.*

object TestHelper {



    fun getFlowNodeObservable(): Observable<List<FlowNode>> = Observable.create<List<FlowNode>> {
        val list = ArrayList<FlowNode>()
        list.add(FlowNode("", "科室审批"))
        list.add(FlowNode("", "部门审批"))
        list.add(FlowNode("", "核稿"))
        list.add(FlowNode("", "结束"))
        it.onNext(list)
        it.onComplete()
    }



}