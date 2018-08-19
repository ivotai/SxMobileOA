package com.unicorn.sxmobileoa.app.base

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class ListFra<Model> : BaseFra() {

    abstract val adapter1: BaseQuickAdapter<Model, BaseViewHolder>

    abstract val recyclerView1: RecyclerView

    abstract val swipeRefreshLayout1: SwipeRefreshLayout

    abstract fun loadPage(page: Int, rows: Int): Observable<List<Model>>

    private val rows = 5

    private val pageNo
        get() = adapter1.data.size / rows

    private val compositeDisposable = CompositeDisposable()

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

    override fun bindIntent() {
        loadFirstPage()
    }

    private fun loadFirstPage() {
        adapter1.data.clear()
        loadPage(page = pageNo, rows = rows)
                .subscribe(object : Observer<List<Model>> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                        compositeDisposable.add(d)
                    }

                    override fun onNext(it: List<Model>) {
                        swipeRefreshLayout1.isRefreshing = false
//                        val response = it.response!!
                        adapter1.setNewData(it)
//                        if (adapter1.data.size == response.data.total) {
//                            adapter1.loadMoreEnd()
//                        }
                    }

                    override fun onError(e: Throwable) {
                        swipeRefreshLayout1.isRefreshing = false
                    }
                })
    }

    private fun loadNextPage() {
        loadPage(page = pageNo, rows = rows)
                .subscribe(object : Observer<List<Model>> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                        compositeDisposable.add(d)
                    }

                    override fun onNext(it: List<Model>) {
                        adapter1.loadMoreComplete()
                        adapter1.addData(it)
                        adapter1.notifyDataSetChanged()
//                        if (adapter1.data.size == response.data.total) {
//                            adapter1.loadMoreEnd()
//                        }
                    }

                    override fun onError(e: Throwable) {
                        adapter1.loadMoreComplete()
                    }
                })
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

}