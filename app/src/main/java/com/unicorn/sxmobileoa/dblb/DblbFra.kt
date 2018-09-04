package com.unicorn.sxmobileoa.dblb

import android.graphics.Color
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import com.blankj.utilcode.util.ConvertUtils
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.base.PageFra
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fra_dblb.*
import java.util.concurrent.TimeUnit


class DblbFra : PageFra<Int>() {

    override val layoutId = R.layout.fra_dblb

    override val swipeRefreshLayout1: SwipeRefreshLayout
        get() = swipeRefreshLayout

    override val recyclerView1: RecyclerView
        get() = recyclerView

    override val adapter1 = DblbAdapter()

    override fun loadPage(page: Int, rows: Int): Single<List<Int>> {
        return Observable.range(1, 10).toList()
                .delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun bindIntent() {
    }

    override fun initViews() {
        titleBar.setTitle("待办列表")
        super.initViews()
        recyclerView.addItemDecoration(
                HorizontalDividerItemDecoration.Builder(context)
                        .color(Color.WHITE)
                        .size(ConvertUtils.dp2px(10f))
                        .build())
    }

}