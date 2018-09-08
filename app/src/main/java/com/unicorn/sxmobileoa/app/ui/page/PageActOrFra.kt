package com.unicorn.sxmobileoa.app.ui.page

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.ui.ActOrFra
import com.unicorn.sxmobileoa.app.ui.page.model.Page
import io.reactivex.Maybe

interface PageActOrFra<Model> : ActOrFra {

    val mRecyclerView: RecyclerView

    val mSwipeRefreshLayout: SwipeRefreshLayout

    val mAdapter: BaseQuickAdapter<Model, BaseViewHolder>

    // loadPage 需要处理线程切换以及销毁时dispose的问题
    fun loadPage(pageNo: Int): Maybe<Page<Model>>

    companion object {
        val rows get() = 5
    }

    private val pageNo
        get() = mAdapter.data.size / rows

    override fun initViews() {
        mSwipeRefreshLayout.setOnRefreshListener { loadFirstPage() }
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary)
        mRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            mAdapter.bindToRecyclerView(this)
            mAdapter.setEnableLoadMore(true)
            mAdapter.setOnLoadMoreListener({ loadNextPage() }, mRecyclerView)
//            mAdapter.setLoadMoreView(SimpleLoadMoreView())
        }
    }

    override fun bindIntent() {
        loadFirstPage()
    }

    private fun loadFirstPage() {
        loadPage(pageNo).subscribe({ page ->
            mSwipeRefreshLayout.isRefreshing = false
            mAdapter.setNewData(page.rows)
            if (mAdapter.data.size == page.total) mAdapter.loadMoreEnd()
        }, {
            mSwipeRefreshLayout.isRefreshing = false
        })
    }

    private fun loadNextPage() {
        loadPage(pageNo).subscribe({ page ->
            mAdapter.loadMoreComplete()
            mAdapter.addData(page.rows)
            mAdapter.notifyDataSetChanged()
            if (mAdapter.data.size == page.total) mAdapter.loadMoreEnd()
        }, {
            mAdapter.loadMoreComplete()
        })
    }

}