package com.unicorn.sxmobileoa.main.dbxx.ui

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import com.blankj.utilcode.util.ConvertUtils
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.app.ui.page.PageActOrFra
import com.unicorn.sxmobileoa.app.ui.page.model.Page
import com.unicorn.sxmobileoa.main.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.main.dbxx.network.DbxxUseCase
import com.unicorn.sxmobileoa.main.main.model.MainItem
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import io.reactivex.Maybe
import kotlinx.android.synthetic.main.act_dbxx.*

class DbxxAct : BaseAct(), PageActOrFra<Dbxx> {

    override val layoutId: Int = R.layout.act_dbxx

    override val mRecyclerView: RecyclerView
        get() = recyclerView

    override val mSwipeRefreshLayout: SwipeRefreshLayout
        get() = swipeRefreshLayout

    override val mAdapter: DbxxAdapter
        get() = realAdapter

    lateinit var realAdapter: DbxxAdapter

    lateinit var moduleCode: String

    override fun loadPage(pageNo: Int): Maybe<Page<Dbxx>> =
// Faker().getDblbMaybe()
    // TODO DELETE FAKER METHOD
            DbxxUseCase(pageNo, mainItem).toMaybe(this)
                    .doAfterSuccess { it.rows.forEach { dbxx -> dbxx.param.moduleCode = moduleCode } }

    lateinit var mainItem: MainItem

    override fun bindIntent() {
        super.bindIntent()
    }

    override fun initViews() {

        mainItem = intent.getSerializableExtra(Key.mainItem) as MainItem
        moduleCode = mainItem.moduleCode
        titleBar.setTitle("待办列表")
        realAdapter = DbxxAdapter(mainItem.moduleCode)
        super.initViews()
        HorizontalDividerItemDecoration.Builder(this)
                .colorResId(R.color.md_grey_100)
                .size(ConvertUtils.dp2px(10f))
                .build().let { mRecyclerView.addItemDecoration(it) }
    }

}
