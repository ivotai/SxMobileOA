package com.unicorn.sxmobileoa.test

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import com.unicorn.sxmobileoa.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fra_general_list.*
import java.util.*
import java.util.concurrent.TimeUnit

class ShouWenFra : PageFra<ShouWen>() {

    override val layoutID = R.layout.fra_general_list

    override val recyclerView1: RecyclerView
        get() = recyclerView

    override val swipeRefreshLayout1: SwipeRefreshLayout
        get() = swipeRefreshLayout

    override val adapter1 = ShouWenAdapter()

    override fun loadPage(page: Int, rows: Int): Observable<java.util.ArrayList<ShouWen>> {
        return Observable.just(ArrayList<ShouWen>().apply {
            add(ShouWen("1"))
            add(ShouWen("2"))
            add(ShouWen("3"))
            add(ShouWen("4"))
            add(ShouWen("5"))
        }
            ).delay(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }

    }
