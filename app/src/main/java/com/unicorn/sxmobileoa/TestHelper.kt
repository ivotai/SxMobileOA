package com.unicorn.sxmobileoa

import com.unicorn.sxmobileoa.business.general.approver.model.Approver
import com.unicorn.sxmobileoa.business.general.flowNode.model.FlowNode
import com.unicorn.sxmobileoa.business.gwgl.GongWen
import io.reactivex.Observable
import java.util.*

object TestHelper {

    fun getShouWenObservable(): Observable<List<GongWen>> = Observable.create<List<GongWen>> {
        val list = ArrayList<GongWen>()
        list.add(GongWen("测试用收文", "渝高法办20185", "审判管理员", "办公室", Date().time))
        list.add(GongWen("测试用收文2", "渝高法办20185", "审判管理员", "办公室", Date().time))
        list.add(GongWen("测试用收文3", "渝高法办20185", "审判管理员", "办公室", Date().time))
        list.add(GongWen("测试用收文4", "渝高法办20185", "审判管理员", "办公室", Date().time))
        list.add(GongWen("测试用收文5", "渝高法办20185", "审判管理员", "办公室", Date().time))
        it.onNext(list)
        it.onComplete()
    }

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