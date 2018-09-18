package com.unicorn.sxmobileoa.header.sbbf

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.clickDate
import com.unicorn.sxmobileoa.app.mess.SpdHelper
import com.unicorn.sxmobileoa.app.textChanges
import com.unicorn.sxmobileoa.header.sbbf.model.Equipment

class EquipmentAdapter : BaseQuickAdapter<Equipment, BaseViewHolder>(R.layout.item_equipment) {

    override fun convert(helper: BaseViewHolder, item: Equipment) {
        val tvBfsbmc = helper.getView<TextView>(R.id.tvBfsbmc)
        val tvPpjxh = helper.getView<TextView>(R.id.tvPpjxh)
        val tvSl = helper.getView<TextView>(R.id.tvSl)
        val tvBfrq = helper.getView<TextView>(R.id.tvBfrq)
        val tvBfjg = helper.getView<TextView>(R.id.tvBfjg)
        val tvSfzx = helper.getView<TextView>(R.id.tvSfzx)
        tvBfsbmc.text = item.bfsbmc
        tvPpjxh.text = item.ppjxh
        tvSl.text = item.sl
        tvBfrq.text = item.bfrq
        tvBfjg.text = item.bfjg
        tvSfzx.text = item.sfzx

        // canEdit
        val nodeId = Global.spd.spdXx.nodeId
        tvBfsbmc.isEnabled = SpdHelper().canEdit2(nodeId)
        tvPpjxh.isEnabled = SpdHelper().canEdit2(nodeId)
        tvSl.isEnabled = SpdHelper().canEdit2(nodeId)

        val flag2 = nodeId in listOf("OA_FLOW_XZZB_SBBF_XXZSJD", "OA_FLOW_XZZB_SBBF_BGS", "OA_FLOW_XZZB_SBBF_BGSSH")
        tvBfrq.isEnabled = flag2
        if (flag2){
            tvBfrq.clickDate()
        }

        // TODO
        tvBfjg.isEnabled = flag2
        val flag3 = nodeId == "OA_FLOW_XZZB_SBBF_GDZCGLKZX"
        tvSfzx.isEnabled = flag3

        // 监控值变化
        tvBfsbmc.textChanges().subscribe { item.bfsbmc = it }
        tvPpjxh.textChanges().subscribe { item.ppjxh = it }
        tvSl.textChanges().subscribe { item.sl = it }
        tvBfrq.textChanges().subscribe { item.bfrq = it }
        tvBfjg.textChanges().subscribe { item.bfjg = it }
        tvSfzx.textChanges().subscribe { item.sfzx = it }
    }

}
