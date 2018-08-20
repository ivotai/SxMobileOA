package com.unicorn.sxmobileoa.business.shouWen.list

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import com.orhanobut.logger.Logger
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.TestHelper
import com.unicorn.sxmobileoa.business.general.GeneralListFra
import com.unicorn.sxmobileoa.business.shouWen.ShouWen
import com.unicorn.sxmobileoa.business.shouWen.ShouWenFetcher
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fra_general_list.*

class ShouWenListFra : GeneralListFra<ShouWen>() {

    override val layoutId = R.layout.fra_general_list

    override val recyclerView1: RecyclerView
        get() = recyclerView

    override val swipeRefreshLayout1: SwipeRefreshLayout
        get() = swipeRefreshLayout

    override val adapter1 = ShouWenAdapter()

    override fun loadPage(page: Int, rows: Int): Observable<List<ShouWen>> {

        ShouWenFetcher().execute().subscribe(object :Observer<Any>{
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: Any) {
                Logger.e(t.toString())
            }

            override fun onError(e: Throwable) {
                Logger.e(e.toString())
            }
        })



        return TestHelper.getShouWenObservable()
//                .delay(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}
