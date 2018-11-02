package com.unicorn.sxmobileoa.n.csx.ui

import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.addDefaultItemDecoration
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.n.csx.network.GetSpcsx
import kotlinx.android.synthetic.main.act_title_recycler.*

class SpcsxAct : BaseAct() {

    override val layoutId = R.layout.act_title_recycler

    override fun initViews() {
        titleBar.setTitle("超审限")
        initRecyclerView()
    }

    private val csxAdapter = CsxAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SpcsxAct)
            csxAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration()
        }
    }

    @SuppressLint("CheckResult")
    override fun bindIntent() {
        GetSpcsx().toMaybe(this).subscribe {
            csxAdapter.setNewData(it)
        }
    }

}
