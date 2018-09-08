package com.unicorn.sxmobileoa.dblb.ui

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import com.blankj.utilcode.util.ConvertUtils
import com.unicorn.sxmobileoa.Faker
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.app.ui.page.PageActOrFra
import com.unicorn.sxmobileoa.app.ui.page.model.Page
import com.unicorn.sxmobileoa.dblb.model.Dblb
import com.unicorn.sxmobileoa.main.model.MainItem
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import io.reactivex.Maybe
import kotlinx.android.synthetic.main.act_dblb.*

class DblbAct : BaseAct(), PageActOrFra<Dblb> {

    override val layoutId: Int = R.layout.act_dblb

    override val mRecyclerView: RecyclerView
        get() = recyclerView

    override val mSwipeRefreshLayout: SwipeRefreshLayout
        get() = swipeRefreshLayout

    override val mAdapter = DblbAdapter()

    override fun loadPage(pageNo: Int): Maybe<Page<Dblb>> = Faker().getDblbMaybe()
        // TODO DELETE FAKER METHOD
//            DblbUseCase(pageNo, mainItem).toMaybe(this)

    lateinit var mainItem: MainItem

    override fun bindIntent() {
        mainItem = intent.getSerializableExtra(Key.mainItem) as MainItem
        super.bindIntent()
    }

    override fun initViews() {
        titleBar.setTitle("待办列表")
        super.initViews()
        HorizontalDividerItemDecoration.Builder(this)
                .colorResId(R.color.md_grey_100)
                .size(ConvertUtils.dp2px(10f))
                .build().let { mRecyclerView.addItemDecoration(it) }
    }

}
