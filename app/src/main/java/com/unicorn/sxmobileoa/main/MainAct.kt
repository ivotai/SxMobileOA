package com.unicorn.sxmobileoa.main

import android.support.v7.widget.LinearLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.main.data.Faker
import com.unicorn.sxmobileoa.main.ui.HeaderView
import com.unicorn.sxmobileoa.main.ui.MainAdapter
import kotlinx.android.synthetic.main.act_main.*

class MainAct : BaseAct() {

    override val layoutId = R.layout.act_main

    override fun initViews() {
        titleBar.setTitle("陕西省高级人民法院移动办公")
    }

    private val mainAdapter = MainAdapter()

    override fun bindIntent() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        mainAdapter.bindToRecyclerView(recyclerView)
        mainAdapter.addHeaderView(HeaderView(this))

        Faker().getMainSection().subscribe {
            mainAdapter.setNewData(it)
        }
    }

}
