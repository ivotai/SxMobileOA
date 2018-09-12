package com.unicorn.sxmobileoa.spd.ui

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.addDefaultItemDecoration
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.SpyjActEvent
import com.unicorn.sxmobileoa.spd.network.ToSpt
import com.unicorn.sxmobileoa.spyj.network.SaveSpd
import com.unicorn.sxmobileoa.spyj.pager.SpyjPagerAct
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.act_spd.*
import kotlinx.android.synthetic.main.footer_view_button.view.*

class SpdAct : BaseAct() {


    override val layoutId = R.layout.act_spd

    lateinit var menu: Menu

    lateinit var dbxx: Dbxx

    override fun initArguments() {
        menu = intent.getSerializableExtra(Key.menu) as Menu
        dbxx = intent.getSerializableExtra(Key.dbxx) as Dbxx
    }

    override fun initViews() {
        titleBar.setTitle(menu.text)
        initRecyclerView()
    }

    private val flowNodeAdapter = FlowNodeAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SpdAct)
            flowNodeAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration()
        }
    }

    override fun bindIntent() {
        getSpd()
    }

    private fun getSpd() {
        ToSpt(menu = menu, dbxx = dbxx).toMaybe(this).subscribe {
            Global.spd = it

            // 处理审批意见
            SpdHelper().copeSpyjList()

            flowNodeAdapter.setNewData(it.flowNodeList)
            val oh = OperationHeaderView(this)
            flowNodeAdapter.addHeaderView(oh)
            val nbfwHeaderView = NbfwHeaderView(this,dbxx)
            flowNodeAdapter.addHeaderView(nbfwHeaderView)
            val fv = ButtonFooterView(this)
            flowNodeAdapter.addFooterView(fv)

            fv.btnSave.safeClicks().subscribe { _ ->

                SaveSpd().toMaybe(this).subscribe {

                }
            }

            fv.btnNextStep.safeClicks().subscribe { _ ->
                //                startActivity(Intent(this@SpdAct, SpyjAct::class.java))
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
