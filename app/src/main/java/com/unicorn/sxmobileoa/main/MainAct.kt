package com.unicorn.sxmobileoa.main

import android.support.v7.widget.GridLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.main.data.Faker
import com.unicorn.sxmobileoa.main.ui.HeaderView
import com.unicorn.sxmobileoa.main.ui.MainAdapter
import kotlinx.android.synthetic.main.act_main.*

class MainAct : BaseAct() {

    override val layoutId = R.layout.act_main

    override fun initViews() {
        titleBar.setTitle(Global.court!!.dmms)
        initRecyclerView()
    }

    private val mainAdapter = MainAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainAct, 4)
            mainAdapter.bindToRecyclerView(this)
            mainAdapter.addHeaderView(HeaderView(this@MainAct))
        }
    }

    override fun bindIntent() {
        Faker().getMainSection().subscribe {
            mainAdapter.setNewData(it)
        }
    }

}
