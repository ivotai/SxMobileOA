package com.unicorn.sxmobileoa.app.mess

import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.mess.model.UserResult
import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.spd.model.SaveSpdResponse
import com.unicorn.sxmobileoa.spd.model.Spd
import com.unicorn.sxmobileoa.spd.model.Spyj
import com.unicorn.sxmobileoa.spdNext.model.*
import org.joda.time.DateTime
import java.util.*

class SpdHelper {

    fun addSpyjIfNeed(dbxx: Dbxx, spd: Spd) {
        val currentNodeId = dbxx.param.nodeId
        val isCustomNode = spd.flowNodeList[0].flowNodeId != null
        val currentFlowNodeList = if (isCustomNode) spd.flowNodeList.filter { flowNode ->
            currentNodeId in flowNode.flowNodeId!!.split(",")
        } else {
            spd.flowNodeList.filter { it.nodeid == currentNodeId }
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
                    spyjNodeName = currentFlowNode.safeSpyjNodeName!!,
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

    fun canEdit2(nodeId: String): Boolean {
        val list = listOf("_SQR", "_NGR", "_QC", "_YBGS", "_LYR", "_TXJDSQ", "_BGSWS", "_NGRB", "_NBYJ", "_SFZBCSP", "_CBQK", "_SWDJ", "_NGYJ")
        return list.any { nodeId.contains(it) }
    }

    fun buildSpdNextParam(spd: Spd, response: SaveSpdResponse, sequenceFlow: NextTaskSequenceFlow, result: UserResult): TaskInstance {
        val nodeId = spd.spdXx.nodeId
        val gd = if (nodeId.contains("_GD")) 1 else 0
        val taskDefKey = sequenceFlow.nextTaskKey
        return TaskInstance(
                processInstanceId = response.processInstancesId,
                taskId = response.taskId,
                taskPerson = TaskPerson(
                        personCode = Global.loginInfo!!.userId,
                        personName = Global.loginInfo!!.userName
                ),
                nextTaskPersonModel = ArrayList<NextTaskPersonModel>().apply {
                    NextTaskPersonModel(
                            taskType = sequenceFlow.nextTaskType,
                            taskDefKey = sequenceFlow.nextTaskKey,
                            approveType = sequenceFlow.approveType,
                            nextPersonCode = result.userIds,
                            nextPersonName = result.userNames,
                            nextLine = sequenceFlow.nextLine
                    ).let { this.add(it) }
                },
                approved = true,
                catagoryCode = response.flowCode,
                approveConent = "审判意见",
                spdId = response.primaryId,
                fydm = Global.court!!.dm,
                moduleCode = response.moduleCode,
                flowCode = response.flowCode,
                nodeId = response.nodeId,
                spyjId = response.spyjId,
                gd = gd,
                nextTaskKey = sequenceFlow.nextTaskKey,
                tasktype = sequenceFlow.nextTaskType,
                spdCode = response.spdCode,
                endTaskType = sequenceFlow.endTaskType,
                reject = "",
                sfjsjd = taskDefKey.toLowerCase() == "end" || taskDefKey == "结束",
                tzstate = false,
                buinessCallBackParams = BuinessCallBackParams("")
        )
    }

}