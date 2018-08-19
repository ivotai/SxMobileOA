package com.unicorn.sxmobileoa.business.shouWen

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.TestHelper
import com.unicorn.sxmobileoa.business.general.GeneralFra
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fra_general_list.*
import java.util.concurrent.TimeUnit

class ShouWenFra : GeneralFra<ShouWen>() {

    override val layoutId = R.layout.fra_general_list

    override val recyclerView1: RecyclerView
        get() = recyclerView

    override val swipeRefreshLayout1: SwipeRefreshLayout
        get() = swipeRefreshLayout

    override val adapter1 = ShouWenAdapter()

    override fun loadPage(page: Int, rows: Int): Observable<List<ShouWen>> {
        return TestHelper.getShouWenObservable()
                .delay(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}
