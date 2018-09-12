package com.unicorn.sxmobileoa.spd.helper

import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.spd.model.Spd
import com.unicorn.sxmobileoa.spd.model.Spyj
import org.joda.time.DateTime
import java.util.*

class SpdHelper {

    fun copeSpyjList(dbxx:Dbxx,spd:Spd) {
//        val currentNodeId = Global.dbxx.param.nodeId
        val currentNodeId = dbxx.param.nodeId
        val currentFlowNodeList = spd.flowNodeList.filter { flowNode ->
            currentNodeId in flowNode.flowNodeId.split(",")
        }
        // 归档，不做任何处理
        if (currentFlowNodeList.isEmpty()) return

        val currentFlowNode = currentFlowNodeList[0]
        val currentSpyjList = currentFlowNode.spyjList.filter { spyj -> spyj.spyjStatus == 0 }


        // 如果不为空，则不做任何处理
        if (!currentSpyjList.isEmpty()) {
        } else {
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


}