package com.unicorn.sxmobileoa.spyj

import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.detail.ui.SpyjBuilder
import kotlinx.android.synthetic.main.act_spyj.*

class SpyjAct : BaseAct() {

    override val layoutId = R.layout.act_spyj

    override fun initViews() {
        titleBar.setTitle("审批意见")
    }

    override fun bindIntent() {
        btnSave.safeClicks().subscribe {
            saveSpyj()
            saveSpd()
        }
    }

    private fun saveSpyj(){
        val currentNodeId = Global.dbxx.param.nodeId
        val currentFlowNodeList = Global.spd.flowNodeList.filter { flowNode ->
            currentNodeId in flowNode.flowNodeId.split(",")
        }
        if (currentFlowNodeList.isEmpty()) return
        val currentFlowNode = currentFlowNodeList[0]
        val currentSpyjList = currentFlowNode.spyjList.filter { spyj -> spyj.spyjStatus == 1 }
        if (currentSpyjList.isEmpty()) {
            currentFlowNode.spyjList.add(SpyjBuilder().build())
        } else {
            Global.spyj = currentSpyjList[0]
        }
    }

    private fun saveSpd(){

    }

}
