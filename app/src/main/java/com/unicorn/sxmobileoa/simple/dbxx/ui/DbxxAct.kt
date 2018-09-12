package com.unicorn.sxmobileoa.simple.dbxx.ui

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import com.blankj.utilcode.util.ConvertUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.app.ui.page.PageActOrFra
import com.unicorn.sxmobileoa.app.ui.page.model.Page
import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.simple.dbxx.network.GetDbxx
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import io.reactivex.Maybe
import kotlinx.android.synthetic.main.act_title_swipe_recycler.*

class DbxxAct : BaseAct(), PageActOrFra<Dbxx> {

    override val layoutId: Int = R.layout.act_title_swipe_recycler

    // 不能使用 late init
    override var mAdapter: BaseQuickAdapter<Dbxx, BaseViewHolder>? = null

    override fun loadPage(pageNo: Int): Maybe<Page<Dbxx>> = GetDbxx(pageNo, menu).toMaybe(this)

    lateinit var menu: Menu

    override fun initArguments() {
        menu = intent.getSerializableExtra(Key.menu) as Menu
    }

    override fun initViews() {
        // 在获取menu后初始化mAdapter
        mAdapter = DbxxAdapter(menu)

        titleBar.setTitle(menu.text)
        super.initViews()
        HorizontalDividerItemDecoration.Builder(this)
                .colorResId(R.color.md_grey_100)
                .size(ConvertUtils.dp2px(10f))
                .build().let { mRecyclerView.addItemDecoration(it) }
    }

    override val mRecyclerView: RecyclerView
        get() = recyclerView

    override val mSwipeRefreshLayout: SwipeRefreshLayout
        get() = swipeRefreshLayout

}
