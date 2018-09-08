package com.unicorn.sxmobileoa.login.court.ui

import android.support.v7.widget.LinearLayoutManager
import com.unicorn.sxmobileoa.Faker
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import kotlinx.android.synthetic.main.act_court.*

class CourtAct : BaseAct() {

    override val layoutId = R.layout.act_court

    private val courtAdapter = CourtAdapter()

    override fun initViews() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            courtAdapter.bindToRecyclerView(this)

            HorizontalDividerItemDecoration.Builder(context)
                    .colorResId(R.color.md_grey_500)
                    .size(1)
                    .build().let { this@apply.addItemDecoration(it) }
        }
    }

    override fun bindIntent() {
//        CourtUseCase()
//                .toMaybe(this)
//                .subscribe { t -> courtAdapter.setNewData(t) }

        // TODO DELETE FAKER METHOD
        Faker().getCourtMaybe().subscribe { t -> courtAdapter.setNewData(t) }
    }

}
