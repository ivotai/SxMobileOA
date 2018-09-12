package com.unicorn.sxmobileoa.spd.helper

import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.spd.model.Spd
import com.unicorn.sxmobileoa.spd.model.Spyj
import io.reactivex.Observable
import io.reactivex.Single
import org.joda.time.DateTime
import java.util.*

class SpdHelper {

    fun addSpyjIfNeed(dbxx: Dbxx, spd: Spd) {
        val currentNodeId = dbxx.param.nodeId
        val currentFlowNodeList = spd.flowNodeList.filter { flowNode ->
            currentNodeId in flowNode.flowNodeId.split(",")
        }

        // 归档，不做任何处理
        if (currentFlowNodeList.isEmpty()) return

        // 当前流程节点
        val currentFlowNode = currentFlowNodeList[0]
        // 当前可编辑审批意见
        val currentSpyjList = currentFlowNode.spyjList.filter { spyj -> spyj.spyjStatus == 0 }
        // 如果为空，则创建审批意见
        if (currentSpyjList.isEmpty()) {
            val spyj = Spyj(
                    createUserId = Global.loginInfo!!.userId,
                    createUserName = Global.loginInfo!!.userName,
                    flowCode = spd.spdXx.flowCode,
                    spyj = "",
                    spyjId = UUID.randomUUID().toString(),
                    spyjNodeId = currentFlowNode.spyjNodeId,
                    spyjNodeName = currentFlowNode.spyjNodeName,
                    spyjSort = currentFlowNode.spyjSort,
                    spyjSprId = Global.loginInfo!!.userId,
                    spyjSprName = Global.loginInfo!!.userName,
                    spyjStatus = 0,
                    spyjYwid = spd.spdXx.id,
                    sysTime = DateTime().toString("yyyy-MM-dd HH:mm:ss")
            )
            currentFlowNode.spyjList.add(spyj)
        }
    }

    fun canEdit(nodeId: String): Single<Boolean> = Observable.fromArray("_SQR", "_NGR", "_QC", "_YBGS", "_LYR",
            "_TXJDSQ", "_BGSWS", "_NGRB", "_NBYJ", "_SFZBCSP"
            , "_CBQK", "_SWDJ", "_NGYJ")
            .any { nodeId.contains(it) }

}