package com.unicorn.sxmobileoa.test

import android.annotation.SuppressLint
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

abstract class PageFra<T> : BaseFra() {

    abstract val adapter1: BaseQuickAdapter<T, BaseViewHolder>

    abstract val recyclerView1: RecyclerView

    abstract val swipeRefreshLayout1: SwipeRefreshLayout

    abstract fun loadPage(page: Int, rows: Int): Observable<ArrayList<T>>

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

    fun loadFirstPage() {
        adapter1.data.clear()
        loadPage(page = pageNo, rows = rows)
                .subscribe(object :Observer<List<T>>{
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                        compositeDisposable.add(d)
                    }

                    override fun onNext(t: List<T>) {
                        swipeRefreshLayout1.isRefreshing = false
//                        val response = it.response!!
//                        adapter1.setNewData(response.data.rows)
                        adapter1.setNewData(t)
//                        if (adapter1.data.size == response.data.total) {
//                            adapter1.loadMoreEnd()
//                        }
                    }

                    override fun onError(e: Throwable) {
                        swipeRefreshLayout1.isRefreshing = false
                    }
                })
    }

    @SuppressLint("CheckResult")
    private fun loadNextPage() {
        loadPage(page = pageNo, rows = rows)
                .subscribe(object :Observer<List<T>>{
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                        compositeDisposable.add(d)
                    }

                    override fun onNext(t: List<T>) {
                      adapter1.loadMoreComplete()
                        adapter1.addData(t)
                        adapter1.notifyDataSetChanged()
//                        adapter1.notifyDataSetChanged()
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