package com.unicorn.sxmobileoa.court.ui

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.startActivityAndFinish
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.app.utils.RxBus
import com.unicorn.sxmobileoa.court.event.CourtSelectEvent
import com.unicorn.sxmobileoa.court.useCase.CourtUseCase
import com.unicorn.sxmobileoa.login.ui.LoginAct
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import florent37.github.com.rxlifecycle.RxLifecycle
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
        CourtUseCase()
                .toMaybe(this)
                .subscribe { t -> courtAdapter.setNewData(t) }

        RxBus.get().toObservable(CourtSelectEvent::class.java)
                .compose(RxLifecycle.disposeOnDestroy(this))
                .subscribe { event ->
                    Global.court = event.court
                    startActivityAndFinish(Intent(this, LoginAct::class.java))
                }
    }

}
