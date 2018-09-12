package com.unicorn.sxmobileoa.spd.ui

import android.support.v7.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.addDefaultItemDecoration
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.helper.SpdHelper
import com.unicorn.sxmobileoa.spd.model.Spd
import com.unicorn.sxmobileoa.spd.network.SaveSpd
import com.unicorn.sxmobileoa.spd.network.ToSpd
import com.unicorn.sxmobileoa.spd.ui.headerView.ButtonFooterView
import com.unicorn.sxmobileoa.spd.ui.headerView.OperationHeaderView
import kotlinx.android.synthetic.main.act_title_recycler.*
import kotlinx.android.synthetic.main.footer_view_button.view.*

abstract class SpdAct : BaseAct() {

    abstract fun addCustomHeaderView()

    override val layoutId = R.layout.act_title_recycler

    lateinit var menu: Menu

    lateinit var dbxx: Dbxx

    lateinit var spd: Spd

    override fun initArguments() {
        menu = intent.getSerializableExtra(Key.menu) as Menu
        dbxx = intent.getSerializableExtra(Key.dbxx) as Dbxx
    }

    override fun initViews() {
        titleBar.setTitle(menu.text)
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
        ToSpd(menu, dbxx).toMaybe(this).subscribe {
            spd = it
            // 添加 taskId
            spd.spdXx.taskId = dbxx.param.taskId

//             处理审批意见
            SpdHelper().addSpyjIfNeed(dbxx, spd)

            flowNodeAdapter.setNewData(spd.flowNodeList)

            //
            addOperationHeaderView()
            addCustomHeaderView()
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
                SaveSpd(spd).toMaybe(this@SpdAct).subscribe {
                    ToastUtils.showShort("保存成功")
                }
            }
            btnNextStep.safeClicks().subscribe { _ ->
                //            ToastUtils.showShort(sp、d.toString())
            }
            flowNodeAdapter.addFooterView(this)
        }
    }

}
