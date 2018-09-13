package com.unicorn.sxmobileoa.spd.ui

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.addDefaultItemDecoration
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.header.BasicHeaderView
import com.unicorn.sxmobileoa.spd.helper.SpdHelper
import com.unicorn.sxmobileoa.spd.model.Spd
import com.unicorn.sxmobileoa.spd.network.SaveSpd
import com.unicorn.sxmobileoa.spd.network.ToSpd
import com.unicorn.sxmobileoa.spd.ui.headerView.ButtonFooterView
import com.unicorn.sxmobileoa.spd.ui.headerView.OperationHeaderView
import com.unicorn.sxmobileoa.spdNext.ui.SpdNextAct
import kotlinx.android.synthetic.main.act_title_recycler.*
import kotlinx.android.synthetic.main.footer_view_button.view.*

abstract class SpdAct : BaseAct() {

    abstract fun addBasicHeaderView(): BasicHeaderView

    override val layoutId = R.layout.act_title_recycler

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
            // 添加 taskId
            spd.spdXx.taskId = model.dbxx.param.taskId

//             处理审批意见
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
        ButtonFooterView(this).apply {
            btnSave.safeClicks().subscribe { _ ->
                // TODO QUESTION! 展开会向 flowNodeList 里添 sub
                // TODO 不采用 textChange 方式 时刻保存到 spd 而是最后再保存
                basicHeaderView.saveToSpd(spd)
                SaveSpd(spd).toMaybe(this@SpdAct).subscribe {
                    ToastUtils.showShort("保存成功")
                }
            }
            btnNextStep.safeClicks().subscribe { _ ->
                startActivity(Intent(this@SpdAct, SpdNextAct::class.java).apply {
                    putExtra(Key.menu, model.menu)
                    putExtra(Key.dbxx, model.dbxx)
                    putExtra(Key.spd, spd)
                })
            }
            flowNodeAdapter.addFooterView(this)
        }
    }

}
