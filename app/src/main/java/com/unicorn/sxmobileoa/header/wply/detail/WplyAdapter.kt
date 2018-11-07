package com.unicorn.sxmobileoa.header.wply.detail

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.mess.MyHolder
import com.unicorn.sxmobileoa.header.wply.model.Wply
import kotlinx.android.synthetic.main.item_wply.*

class WplyAdapter : BaseQuickAdapter<Wply, MyHolder>(R.layout.item_wply) {

    override fun convert(helper: MyHolder, item: Wply) {
        helper.apply {
            tvWpmc.text = item.wpmc
            tvGg.text = item.gg
            tvSqsl.text = item.sqsl
            tvSlsl.setText(item.slsl)
            tvZkff.setText(item.zkff)

            // 是否可以编辑
            val nodeId = Global.spd.nodeModel_1!!.nodeid
            val canEdit = nodeId in listOf("OA_FLOW_HQGL_WPLY_KG", "OA_FLOW_HQGL_WPLY_WZGLYBL", "OA_FLOW_HQGL_WPLY_CGFF", "OA_FLOW_HQGL_WPLY_BGSSP", "OA_FLOW_HQGL_WPLY_HQFWZXZWK")
            tvSlsl.isEnabled = canEdit
            tvZkff.isEnabled = canEdit
        }

        // 监控值变化
//        tvWpmc.textChanges2().subscribe { item.wpmc = it }
//        tvGg.textChanges2()8.subscribe { item.gg = it }
//        tvSqsl.textChanges2().subscribe { item.sqsl = it }
//        tvSlsl.textChanges2().subscribe { item.slsl = it }
//        tvZkff.textChanges2().subscribe { item.zkff = it }
    }

}


//            tvWpmc.clickCode("物品名称", "WPLY_WP", item.key_wpmc)
//RxBus.get().registerEvent(TextResult::class.java, mContext as LifecycleOwner, Consumer {
//    item.spd.set(it.key, it.result)
//    notifyDataSetChanged()
//})