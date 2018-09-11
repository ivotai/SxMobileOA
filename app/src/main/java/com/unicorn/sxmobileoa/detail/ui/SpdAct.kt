package com.unicorn.sxmobileoa.detail.ui

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.app.utils.RxBus
import com.unicorn.sxmobileoa.detail.SpyjActEvent
import com.unicorn.sxmobileoa.detail.network.SpdUseCase
import com.unicorn.sxmobileoa.spyj.network.SaveSpd
import com.unicorn.sxmobileoa.spyj.pager.SpyjPagerAct
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.act_spd.*
import kotlinx.android.synthetic.main.footer_view_button.view.*

class SpdAct : BaseAct() {


    override val layoutId = R.layout.act_spd

    override fun initViews() {
        titleBar.setTitle(Global.dbxx.mainItem!!.text)
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
        SpdUseCase(Global.dbxx).toMaybe(this).subscribe {
            Global.spd = it
            flowNodeAdapter.setNewData(it.flowNodeList)
            val oh = OperationHeaderView(this)
            flowNodeAdapter.addHeaderView(oh)
            val nbfwHeaderView = NbfwHeaderView(this)
            flowNodeAdapter.addHeaderView(nbfwHeaderView)
            val fv = ButtonFooterView(this)
            flowNodeAdapter.addFooterView(fv)

            fv.btnSave.safeClicks().subscribe { _ ->

                SaveSpd().toMaybe(this).subscribe {

                }
            }

            fv.btnNextStep.safeClicks().subscribe { _ ->

            }
        }
    }

    override fun registerEvent() {
        RxBus.get().registerEvent(SpyjActEvent::class.java, this, Consumer { event ->
            startActivity(Intent(this, SpyjPagerAct::class.java).apply {
                putExtra(Key.position, event.position)
                ToastUtils.showShort(event.position.toString())
            })
        })
    }
}
