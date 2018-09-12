package com.unicorn.sxmobileoa.spd.model

import com.chad.library.adapter.base.entity.IExpandable
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.unicorn.sxmobileoa.spd.ui.FlowNodeAdapter

data class Spd(
        val cyy: List<Cyy>,
        val flowNodeList: List<FlowNode>,
        val spdData: List<SpdData>,
        val spdXx: SpdXx,
        val nodeModel: NodeModel
)

data class FlowNode(
        val flowCode: String,
        val flowNodeId: String,
        val formIndex: Int,
        val gdlx: String,
        val jdsfmrxz: String,
        val nodeid: String,
        val sfngr: Int,
        val sfqy: Int,
        val sftxspyj: Int,
        val sfzdgd: Int,
        val smsTepId: String,
        val spdCode: String,
        val spyjId: String,
        val spyjList: MutableList<Spyj>,
        val spyjNodeId: String,
        val spyjNodeName: String,
        val spyjSort: Int,
        val spyjStatus: Int,
        val spyjsfbt: String,
        val whsfbt: Int,
        val yjsfzwlj: String
) : IExpandable<Spyj>,MultiItemEntity {
    override fun getSubItems(): MutableList<Spyj> {
        return spyjList
    }

    var b = false

    override fun isExpanded(): Boolean {
        return b
    }

    override fun getLevel(): Int {
        return 0
    }

    override fun setExpanded(expanded: Boolean) {
        b = expanded
    }

    override fun getItemType() =            FlowNodeAdapter.type_flow_node

}

data class SpdXx(
        val assignName: String,
        val blsj: String,
        val bmbm: String,
        val bmmc: String,
        val bt: String,
        val column1: String,
        val column10: String,
        val column2: String,
        val column3: String,
        val column4: String,
        val column5: String,
        val column6: String,
        val column7: String,
        val column8: String,
        val column9: String,
        val createUserId: String,
        val createUserName: String,
        val dbNodeId: String,
        val dbsprId: String,
        val dbsprmc: String,
        val dcllqtfyspdBfyCt: Int,
        val dcllqtfyspdCt: Int,
        val dcllqtfyspwcCt: Int,
        val dqh: Int,
        val dycount: Int,
        val fbrid: String,
        val fbrmc: String,
        val fbsj: String,
        val flowCode: String,
        val flowName: String,
        val flowParams: Any,
        val funCode: String,
        val fydm: String,
        val fyjc: String,
        val fymc: String,
        val gdbz: Int,
        val gdlx: String,
        val gdrId: String,
        val gdrmc: String,
        val gdsj: String,
        val gdzt: Int,
        val hisNodeId: String,
        val hisNodeName: String,
        val hjjexx: Int,
        val htmlPath: String,
        val id: String,
        val imgPath: String,
        val isUpdateIndex: String,
        val isfb: Int,
        val jsrId: String,
        val jssj: String,
        val lcmc: String,
        val moduleCode: String,
        val ndh: String,
        val nodeId: String,
        val nodeName: String,
        val processInstancesId: String,
        val qhfs: String,
        val qybz: Int,
        val remoteTaskName: String,
        val sdwh: String,
        val sfbl: String,
        val sfdy: Int,
        val sfth: String,
        val sfyxgq: Int,
        val sfzdgd: Int,
        val sjrdz: String,
        val sjrlxfs: String,
        val sjrmc: String,
        val sort: String,
        val spdCode: String,
        val spdJson: String,
        val spdNodeName: String,
        val spdSprmc: String,
        val spdTaskNodeHis: String,
        val spdVersion: Int,
        val spdid: String,
        val sprid: String,
        val sprmc: String,
        val startFlow: String,
        val status: String,
        val sysTime: String,
        var taskId: String,
        val updateTime: String,
        val url: String,
        val wh: String,
        val whid: String,
        val wjcjfs: Int,
        val xtlx: String,
        val ycid: String,
        val ycrs_input: String,
        val ycspdid: String,
        val ytbm_input: String,
        val ytmc_input: String,
        val ytspdid: String,
        val zfbz: Int,
        val zhmc: String
)

data class NodeModel(
        val flowCode: String,
        val flowNodeId: String,
        val formIndex: Int,
        val gdlx: String,
        val jdsfmrxz: String,
        val nodeid: String,
        val sfngr: Int,
        val sfqy: Int,
        val sftxspyj: Int,
        val sfzdgd: Int,
        val smsTepId: String,
        val spdCode: String,
        val spyjId: String,
        val spyjList: List<Any>,
        val spyjNodeId: String,
        val spyjNodeName: String,
        val spyjSort: Int,
        val spyjStatus: Int,
        val spyjsfbt: String,
        val whsfbt: Int,
        val yjsfzwlj: String
)

data class Cyy(
        val content: String,
        val courtCode: String,
        val id: String,
        val system: String,
        val userId: String,
        val xssx: String
)

data class SpdData(
        val create: Boolean,
        val createUserId: String,
        val createUserName: String,
        val dataType: String,
        val id: String,
        val qybz: Int,
        val spdKey: String,
        var spdValue: String,
        val spdid: String,
        val sysTime: String,
        var update: Boolean,
        var updateTime: String,
        val xtlx: String
)

data class Spyj(
        var createUserId: String,
        var createUserName: String,
        var flowCode: String,
        val flowName: String = "",
        val formIndex: Int = 0,
        val gdlx: String = "",
        val id: String = "",
        val jdsfmrxz: Int = 0,
        val qybz: Int = 0,
        val sfdxtz: Int = 0,
        val sfngr: Int = 0,
        val sfqy: Int = 0,
        val sftxspyj: Int = 0,
        val sfyjtz: Int = 0,
        val sfzdgd: Int = 0,
        val smsTepId: String = "",
        val spdCode: String = "",
        var spyj: String,
        var spyjId: String,
        val spyjNodeId: String,
        val spyjNodeName: String,
        val spyjSort: Int,
        var spyjSprId: String,
        var spyjSprName: String,
        var spyjStatus: Int,
        var spyjYwid: String,
        val spyjsfbt: Int = 0,
        var sysTime: String,
        val tzbt: String = "",
        val tzfw: Int = 0,
        val tzjd: String = "",
        val tznr: String = "",
        val updateTime: String = "",
        val whsfbt: Int = 0,
        val xtlx: String = "",
        val yjsfzwlj: Int = 0
) : MultiItemEntity {
    override fun getItemType() =
            FlowNodeAdapter.type_spyj
}