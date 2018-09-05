package com.unicorn.sxmobileoa.app.base

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import florent37.github.com.rxlifecycle.RxLifecycle
import io.reactivex.Single

abstract class PageFra<Model> : BaseFra() {

    abstract val adapter1: BaseQuickAdapter<Model, BaseViewHolder>

    abstract val recyclerView1: RecyclerView

    abstract val swipeRefreshLayout1: SwipeRefreshLayout

    abstract fun loadPage(page: Int, rows: Int): Single<List<Model>>

    private val rows = 5

    private val pageNo
        get() = adapter1.data.size / rows

    override fun initViews() {
        swipeRefreshLayout1.setOnRefreshListener { loadFirstPage() }
        swipeRefreshLayout1.setColorSchemeResources(R.color.colorPrimary)
        recyclerView1.apply {
            layoutManager = LinearLayoutManager(context)
            adapter1.bindToRecyclerView(this)
            adapter1.setEnableLoadMore(true)
            adapter1.setOnLoadMoreListener({ loadNextPage() }, recyclerView1)
//            adapter1.setLoadMoreView(SimpleLoadMoreView())
        }
    }

    override fun onSupportVisible() {
        loadFirstPage()
    }

    private fun loadFirstPage() {
        loadPage(page = pageNo, rows = rows)
                .compose(RxLifecycle.disposeOnDestroy(this))
                .subscribe({
                    swipeRefreshLayout1.isRefreshing = false
//                        adapter1.data.clear()
//  val response = it.response!!
                    adapter1.setNewData(it)
//                        if (adapter1.data.size == response.data.total) {
//                            adapter1.loadMoreEnd()
//                        }
                },{
                    swipeRefreshLayout1.isRefreshing = false

                })
    }

    private fun loadNextPage() {
        loadPage(page = pageNo, rows = rows)
                .compose(RxLifecycle.disposeOnDestroy(this))
                .subscribe({
                    adapter1.loadMoreComplete()
                    adapter1.addData(it)
                    adapter1.notifyDataSetChanged()
//                        if (adapter1.data.size == response.data.total) {
//                            adapter1.loadMoreEnd()
//                        }
                }, {
                    adapter1.loadMoreComplete()
                })
    }

}