package com.unicorn.sxmobileoa.simple.dbxx.ui

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import com.blankj.utilcode.util.ConvertUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.app.ui.page.PageActOrFra
import com.unicorn.sxmobileoa.app.ui.page.model.Page
import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.simple.dbxx.network.GetDbxx
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import dart.DartModel
import io.reactivex.Maybe
import kotlinx.android.synthetic.main.act_title_swipe_recycler.*

class DbxxAct : BaseAct(), PageActOrFra<Dbxx> {

    override var mAdapter: BaseQuickAdapter<Dbxx, BaseViewHolder>? = null

    override fun loadPage(pageNo: Int): Maybe<Page<Dbxx>> = GetDbxx(pageNo, model.menu).toMaybe(this)

    override fun initViews() {
        titleBar.setTitle(model.menu.text)
        mAdapter = DbxxAdapter(model.menu)
        super.initViews()
        HorizontalDividerItemDecoration.Builder(this)
                .colorResId(R.color.md_grey_100)
                .size(ConvertUtils.dp2px(10f))
                .build().let { mRecyclerView.addItemDecoration(it) }
    }

    // ===================== ignore =====================

    @DartModel
    lateinit var model: DbxxActNavigationModel

    override val layoutId: Int = R.layout.act_title_swipe_recycler

    override val mRecyclerView: RecyclerView
        get() = recyclerView

    override val mSwipeRefreshLayout: SwipeRefreshLayout
        get() = swipeRefreshLayout

}
