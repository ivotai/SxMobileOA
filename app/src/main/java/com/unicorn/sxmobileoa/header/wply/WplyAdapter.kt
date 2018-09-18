package com.unicorn.sxmobileoa.header.wply

import android.arch.lifecycle.LifecycleOwner
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.clickCode
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.mess.SpdHelper
import com.unicorn.sxmobileoa.app.mess.model.TextResult
import com.unicorn.sxmobileoa.app.set
import com.unicorn.sxmobileoa.app.textChanges
import com.unicorn.sxmobileoa.header.wply.model.Wply
import io.reactivex.functions.Consumer

class WplyAdapter : BaseQuickAdapter<Wply, BaseViewHolder>(R.layout.item_wply) {

    override fun convert(helper: BaseViewHolder, item: Wply) {
        val tvWpmc = helper.getView<TextView>(R.id.tvWpmc)
        val tvGg = helper.getView<TextView>(R.id.tvGg)
        val tvSqsl = helper.getView<TextView>(R.id.tvSqsl)
        val tvSlsl = helper.getView<TextView>(R.id.tvSlsl)
        val tvZkff = helper.getView<TextView>(R.id.tvZkff)
        tvWpmc.text = item.wpmc
        tvGg.text = item.gg
        tvSqsl.text = item.sqsl
        tvSlsl.text = item.slsl
        tvZkff.text = item.zkff

        // canEdit
        val nodeId = Global.spd.spdXx.nodeId
        val flag1 = SpdHelper().canEdit2(nodeId)
        if (flag1) {
            tvWpmc.clickCode("物品名称", "WPLY_WP", item.key_wpmc)
        }
        tvGg.isEnabled = flag1
        tvSqsl.isEnabled = flag1

        val flag2 = nodeId in listOf("OA_FLOW_HQGL_WPLY_KG", "OA_FLOW_HQGL_WPLY_WZGLYBL", "OA_FLOW_HQGL_WPLY_CGFF",
                "OA_FLOW_HQGL_WPLY_BGSSP", "OA_FLOW_HQGL_WPLY_HQFWZXZWK")
        tvSlsl.isEnabled = flag2
        tvZkff.isEnabled = flag2

        RxBus.get().registerEvent(TextResult::class.java, mContext as LifecycleOwner, Consumer {
            item.spd.set(it.key,  it.result)
            notifyDataSetChanged()
        })

        // 监控值变化
        tvWpmc.textChanges().subscribe { item.wpmc = it }
        tvGg.textChanges().subscribe { item.gg = it }
        tvSqsl.textChanges().subscribe { item.sqsl = it }
        tvSlsl.textChanges().subscribe { item.slsl = it }
        tvZkff.textChanges().subscribe { item.zkff = it }
    }

}
