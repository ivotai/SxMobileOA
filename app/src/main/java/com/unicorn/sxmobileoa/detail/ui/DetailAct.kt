package com.unicorn.sxmobileoa.detail.ui

import android.support.v7.widget.LinearLayoutManager
import com.orhanobut.logger.Logger
import com.unicorn.sxmobileoa.Faker
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.detail.model.Detail
import com.unicorn.sxmobileoa.main.dbxx.model.Dbxx
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import kotlinx.android.synthetic.main.act_detail.*

class DetailAct : BaseAct() {

    private lateinit var dbxx: Dbxx
    private lateinit var detail: Detail

    override fun initArguments() {
//        dbxx = intent.getSerializableExtra(Key.dbxx) as Dbxx
    }

    override val layoutId = R.layout.act_detail

    override fun initViews() {
//        titleBar.setTitle(dbxx.mainItem!!.text)
        initRecyclerView()
    }

    private val flowNodeAdapter = FlowNodeAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@DetailAct)
            flowNodeAdapter.bindToRecyclerView(this)
        }
        HorizontalDividerItemDecoration.Builder(this@DetailAct)
                .colorResId(R.color.md_grey_300)
                .size(1)
                .build().let { recyclerView.addItemDecoration(it) }
    }

    override fun bindIntent() {
        getDetail()
    }

    private fun getDetail() {
//        DetailUseCase(dbxx).toMaybe(this)


        Faker().getDetailMaybe().subscribe {
            detail = it
            it.flowNodeList.forEach { flowNode ->
                //                flowNode.dbxx = dbxx
            }
            flowNodeAdapter.setNewData(it.flowNodeList)
            val oh = OperationHeaderView(this)
            flowNodeAdapter.addHeaderView(oh)
            val nbfwHeaderView = NbfwHeaderView(this, detail)
            flowNodeAdapter.addHeaderView(nbfwHeaderView)
            val fv = ButtonFooterView(this)
            flowNodeAdapter.addFooterView(fv)

            fv.btnSave.safeClicks().subscribe {
                Logger.e(detail.toString())
            }
        }
    }

}
