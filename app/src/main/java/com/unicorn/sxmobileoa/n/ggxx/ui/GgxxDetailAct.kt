package com.unicorn.sxmobileoa.n.ggxx.ui

import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.addDefaultItemDecoration
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.n.attachment.AttachmentAdapter
import com.unicorn.sxmobileoa.n.ggxx.model.Ggxx
import com.unicorn.sxmobileoa.n.ggxx.network.GetGgxxDetail
import com.unicorn.sxmobileoa.spd.model.Fj
import kotlinx.android.synthetic.main.title_recycler.*

class GgxxDetailAct : BaseAct() {

    override val layoutId = R.layout.title_recycler

    override fun initViews() {
        titleBar.setTitle("公告详情")
        initRecyclerView()
    }

    val mAdapter = AttachmentAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@GgxxDetailAct)
            mAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration()
        }
    }

    override fun bindIntent() {
        getDetail()
    }

    @SuppressLint("CheckResult")
    private fun getDetail() {
        // TODO GgxxHeaderView
        val id = intent.getStringExtra(Key.id)
        GetGgxxDetail(id).toMaybe(this).subscribe { setData(ggxx = it) }
    }

    private fun setData(ggxx: Ggxx) {
        ggxx.apply {
            filenameArr.zip(filepathArr) { name, path -> Fj(fjmc = name, fjdz = path) }
                    .let { mAdapter.setNewData(it) }
            val headerView = GgxxHeaderView(this@GgxxDetailAct,ggxx)
            mAdapter.addHeaderView(headerView)
        }
    }

}
