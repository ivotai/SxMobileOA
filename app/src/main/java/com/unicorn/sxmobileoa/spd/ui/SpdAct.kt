package com.unicorn.sxmobileoa.spd.ui

import android.support.v7.widget.LinearLayoutManager
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
import com.unicorn.sxmobileoa.spd.ui.headerView.NbfwHeaderView
import com.unicorn.sxmobileoa.spd.ui.headerView.OperationHeaderView
import kotlinx.android.synthetic.main.act_title_recycler.*
import kotlinx.android.synthetic.main.footer_view_button.view.*

class SpdAct : BaseAct() {

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

    private val flowNodeAdapter = FlowNodeAdapter()

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

            // 处理审批意见
            SpdHelper().addSpyjIfNeed(dbxx, spd)

            // for expandable
            spd.flowNodeList.forEach { flowNode ->
                flowNode.subItems = flowNode.spyjList
            }
            flowNodeAdapter.setNewData(spd.flowNodeList)

            //
            addOperationHeaderView()
            addNbfwHeaderView()
            addFooterView()
        }
    }

    private fun addOperationHeaderView() {
        val headerView = OperationHeaderView(this)
        flowNodeAdapter.addHeaderView(headerView)
    }

    private fun addNbfwHeaderView() {
        val headerView = NbfwHeaderView(this, dbxx, spd)
        flowNodeAdapter.addHeaderView(headerView)
    }

    private fun addFooterView() {
        val footerView = ButtonFooterView(this)
        footerView.btnSave.safeClicks().subscribe { _ ->
            SaveSpd(spd).toMaybe(this).subscribe {

            }
        }
        footerView.btnNextStep.safeClicks().subscribe { _ ->
            //
        }
        flowNodeAdapter.addFooterView(footerView)
    }

}
