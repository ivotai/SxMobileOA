package com.unicorn.sxmobileoa.dblb

import android.graphics.Color
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import com.blankj.utilcode.util.ConvertUtils
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.app.ui.PageActOrFra
import com.unicorn.sxmobileoa.app.utils.MainThreadTransformer
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import io.reactivex.Observable
import io.reactivex.Single
import kotlinx.android.synthetic.main.act_dblb.*
import java.util.concurrent.TimeUnit

class DblbAct : BaseAct(), PageActOrFra<Int> {

    override val layoutId: Int = R.layout.act_dblb

    override val recyclerView1: RecyclerView
        get() = recyclerView

    override val swipeRefreshLayout1: SwipeRefreshLayout
        get() = swipeRefreshLayout

    override val adapter1 = DblbAdapter()

    override fun loadPage(page: Int, rows: Int): Single<List<Int>> {
        return Observable.just(1, 2).toList()
                .delay(2, TimeUnit.SECONDS)
                .compose(MainThreadTransformer())
    }

    override fun initViews() {
        titleBar.setTitle("待办列表")
        super.initViews()
        recyclerView.addItemDecoration(
                HorizontalDividerItemDecoration.Builder(this)
                        .color(Color.WHITE)
                        .size(ConvertUtils.dp2px(10f))
                        .build())
    }

}
