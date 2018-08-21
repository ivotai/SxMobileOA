package com.unicorn.sxmobileoa

import com.unicorn.sxmobileoa.business.general.approver.model.Approver
import com.unicorn.sxmobileoa.business.general.flowNode.model.FlowNode
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

    fun getApproverObservable(): Observable<List<Approver>> = Observable.create<List<Approver>> {
        val list = ArrayList<Approver>()
        list.add(Approver( "罗勇"))
        list.add(Approver( "李国强"))
        list.add(Approver( "罗飞明"))
        list.add(Approver( "赵程"))
        it.onNext(list)
        it.onComplete()
    }

}