package com.unicorn.sxmobileoa.header.sbly

import android.arch.lifecycle.LifecycleOwner
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.clickCode
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.mess.model.TextResult
import com.unicorn.sxmobileoa.app.set
import com.unicorn.sxmobileoa.app.textChanges2
import io.reactivex.functions.Consumer

class SblyAdapter : BaseQuickAdapter<Sbly, BaseViewHolder>(R.layout.item_sbly) {

    override fun convert(helper: BaseViewHolder, item: Sbly) {
        val tvWpmc = helper.getView<TextView>(R.id.tvWpmc)
        val tvSl = helper.getView<TextView>(R.id.tvSl)
        val tvGg = helper.getView<TextView>(R.id.tvGg)
        val tvPp = helper.getView<TextView>(R.id.tvPp)
        val tvBz = helper.getView<TextView>(R.id.tvBz)
        tvWpmc.text = item.wpmc
        tvSl.text = item.sl
        tvGg.text = item.gg
        tvPp.text = item.pp
        tvBz.text = item.bz

        val nodeId = Global.spd.spdXx.nodeId
        val flag = nodeId in listOf("OA_FLOW_XZZB_SBLY_XXZXQRCG", "OA_FLOW_XZZB_SBLY_BGSSH", "OA_FLOW_XZZB_SBLY_XXZXQDCGHW")
        if(flag){
            tvWpmc.clickCode("设备名称", "SBLY_WP", item.key_wpmc)
        }
        tvSl.isEnabled = flag
        tvGg.isEnabled = flag
        tvPp.isEnabled = flag
        tvBz.isEnabled = flag

        RxBus.get().registerEvent(TextResult::class.java, mContext as LifecycleOwner, Consumer {
            item.spd.set(it.key,  it.result)
            notifyDataSetChanged()
        })

        // 监控值变化
        tvWpmc.textChanges2().subscribe { item.wpmc = it }
        tvSl.textChanges2().subscribe { item.sl = it }
        tvGg.textChanges2().subscribe { item.gg = it }
        tvPp.textChanges2().subscribe { item.pp = it }
        tvBz.textChanges2().subscribe { item.bz = it }
    }

}
