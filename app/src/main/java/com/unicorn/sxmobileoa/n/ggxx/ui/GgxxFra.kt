package com.unicorn.sxmobileoa.n.ggxx.ui

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.ui.BaseFra
import com.unicorn.sxmobileoa.app.ui.page.PageActOrFra
import com.unicorn.sxmobileoa.app.ui.page.model.Page
import com.unicorn.sxmobileoa.n.ggxx.model.Ggxx
import com.unicorn.sxmobileoa.n.ggxx.network.GetGgxx
import io.reactivex.Maybe
import kotlinx.android.synthetic.main.act_swipe_recycler.*

class GgxxFra : BaseFra(), PageActOrFra<Ggxx> {

    override val mRecyclerView: RecyclerView
        get() = recyclerView
    override val mSwipeRefreshLayout: SwipeRefreshLayout
        get() = swipeRefreshLayout

    override var mAdapter: BaseQuickAdapter<Ggxx, BaseViewHolder>? = GgxxAdapter()

    override fun loadPage(pageNo: Int): Maybe<Page<Ggxx>> {
        val isRead = arguments!!.getString(Key.isRead)
        return GetGgxx(pageNo, isRead).toMaybe(this)
    }

    override val layoutId = R.layout.act_swipe_recycler

}