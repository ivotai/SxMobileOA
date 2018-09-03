package com.unicorn.sxmobileoa.remove.business.gwgl

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.remove.business.general.GeneralListFra
import com.unicorn.sxmobileoa.remove.business.gwgl.fwlc.Fwlc
import com.unicorn.sxmobileoa.remove.business.gwgl.fwlc.FwlcFetcher
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fra_general_list.*

 class GongWenListFra : GeneralListFra<Fwlc>() {

    override val layoutId = R.layout.fra_general_list

    override val recyclerView1: RecyclerView
        get() = recyclerView

    override val swipeRefreshLayout1: SwipeRefreshLayout
        get() = swipeRefreshLayout

    override val adapter1 = GongWenAdapter()

    override fun loadPage(page: Int, rows: Int): Observable<List<Fwlc>> {
        val type = arguments!!.getString("type")
        return FwlcFetcher(type, page).execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { it.data.rows }
    }

}
