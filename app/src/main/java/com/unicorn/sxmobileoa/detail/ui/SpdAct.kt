package com.unicorn.sxmobileoa.detail.ui

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.orhanobut.logger.Logger
import com.unicorn.sxmobileoa.Faker
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.app.utils.RxBus
import com.unicorn.sxmobileoa.detail.SpyjActEvent
import com.unicorn.sxmobileoa.spyj.SpyjAct
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.act_spd.*
import kotlinx.android.synthetic.main.footer_view_button.view.*

class SpdAct : BaseAct() {


    override val layoutId = R.layout.act_spd

    override fun initViews() {
//        titleBar.setTitle(dbxx.mainItem!!.text)
        initRecyclerView()
    }

    private val flowNodeAdapter = FlowNodeAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SpdAct)
            flowNodeAdapter.bindToRecyclerView(this)
        }
        HorizontalDividerItemDecoration.Builder(this@SpdAct)
                .colorResId(R.color.md_grey_300)
                .size(1)
                .build().let { recyclerView.addItemDecoration(it) }
    }

    override fun bindIntent() {
        getSpd()
    }

    private fun getSpd() {
//        DetailUseCase(dbxx).toMaybe(this)


        Faker().getDetailMaybe().subscribe {
            Global.detail = it
//            it.flowNodeList.forEach { flowNode ->
//                                flowNode.dbxx = dbxx
//            }
            flowNodeAdapter.setNewData(it.flowNodeList)
            val oh = OperationHeaderView(this)
            flowNodeAdapter.addHeaderView(oh)
            val nbfwHeaderView = NbfwHeaderView(this)
            flowNodeAdapter.addHeaderView(nbfwHeaderView)
            val fv = ButtonFooterView(this)
            flowNodeAdapter.addFooterView(fv)

            fv.btnSave.safeClicks().subscribe { _ ->
                Logger.e(Global.detail.toString())
            }

            fv.btnNextStep.safeClicks().subscribe { _ ->
                val list = Global.detail.flowNodeList.filter { flowNode ->
                    Global.dbxx.param.nodeId in flowNode.flowNodeId.split(",")
                }

                if (list[0].spyjList.any { spyj -> spyj.spyjStatus == 1 }) {
                    Global.spyj = list[0].spyjList.filter { spyj -> spyj.spyjStatus == 1 }[0]
                } else {
                    list[0].spyjList.add(SpyjBuilder().build())
                }

            }
        }
    }

    override fun registerEvent() {
        RxBus.get().registerEvent(SpyjActEvent::class.java, this, Consumer { event ->
            startActivity(Intent(this, SpyjAct::class.java).apply {
                putExtra(Key.position, event.position)
                ToastUtils.showShort(event.position.toString())
            })
        })
    }
}
