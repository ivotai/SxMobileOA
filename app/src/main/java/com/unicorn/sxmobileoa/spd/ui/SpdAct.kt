package com.unicorn.sxmobileoa.spd.ui

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.addDefaultItemDecoration
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.mess.SpdHelper
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.commitTask.model.CommitSuccess
import com.unicorn.sxmobileoa.commitTask.ui.CommitTaskAct
import com.unicorn.sxmobileoa.header.BasicHeaderView
import com.unicorn.sxmobileoa.spd.model.Spd
import com.unicorn.sxmobileoa.spd.model.SpdActNavigationModel
import com.unicorn.sxmobileoa.spd.network.saveSpd.SaveSpd
import com.unicorn.sxmobileoa.spd.network.toSpd.ToSpd
import com.unicorn.sxmobileoa.spd.ui.headerView.ButtonFooterView
import com.unicorn.sxmobileoa.spd.ui.headerView.OperationHeaderView
import dart.DartModel
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.act_title_recycler.*
import kotlinx.android.synthetic.main.footer_view_button.view.*

abstract class SpdAct : BaseAct() {
    override fun registerEvent() {
       RxBus.get().registerEvent(CommitSuccess::class.java,this, Consumer {
           finish()
       })
    }

    abstract fun addBasicHeaderView(): BasicHeaderView

    override val layoutId = R.layout.act_title_recycler

    @DartModel
    lateinit var model: SpdActNavigationModel
    lateinit var spd: Spd
    private lateinit var basicHeaderView: BasicHeaderView

    override fun initViews() {
        titleBar.setTitle(model.menu.text)
        initRecyclerView()
    }

    val flowNodeAdapter = FlowNodeAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SpdAct)
            flowNodeAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration()
        }
    }

    override fun bindIntent() {
        ToSpd(model.menu, model.dbxx).toMaybe(this).subscribe {
            spd = it

            // 供 equipmentAct 使用。
            Global.spd = spd
            // 添加 taskId
            spd.spdXx.taskId = model.dbxx.param.taskId

//            处理审批意见9
             SpdHelper().addSpyjIfNeed(model.dbxx, spd)

            flowNodeAdapter.setNewData(spd.flowNodeList)

            //
            addOperationHeaderView()
            basicHeaderView = addBasicHeaderView()
            addFooterView()
        }
    }

    private fun addOperationHeaderView() {
        OperationHeaderView(this).apply {
            flowNodeAdapter.addHeaderView(this)
        }
    }

    private fun addFooterView() {
        val footer = ButtonFooterView(this)
        flowNodeAdapter.addFooterView(footer)
        footer.btnSave.safeClicks().subscribe { _ ->
            // TODO QUESTION! 展开会向 flowNodeList 里添 sub
            // TODO 不采用 textChange 方式 时刻保存到 spd 而是最后再保存
            basicHeaderView.saveToSpd(spd)
            SaveSpd(spd).toMaybe(this@SpdAct).subscribe {
                ToastUtils.showShort("保存成功")
            }
        }
        footer.btnNextStep.safeClicks().subscribe { _ ->
            basicHeaderView.saveToSpd(spd)
            SaveSpd(spd).toMaybe(this@SpdAct).subscribe {
                startActivity(Intent(this@SpdAct, CommitTaskAct::class.java).apply {
                    putExtra(Key.menu, model.menu)
                    putExtra(Key.dbxx, model.dbxx)
                    putExtra(Key.spd, spd)
                    putExtra(Key.saveSpdResponse, it)
                })
            }
        }
    }

}
