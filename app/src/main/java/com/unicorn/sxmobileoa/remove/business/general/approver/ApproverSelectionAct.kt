package com.unicorn.sxmobileoa.remove.business.general.approver

import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.TestHelper
import com.unicorn.sxmobileoa.app.base.BaseAct
import com.unicorn.sxmobileoa.remove.business.general.approver.model.Approver
import com.unicorn.sxmobileoa.remove.business.general.checked.CheckedWrapper
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.act_approver_selection.*
import java.util.*

class ApproverSelectionAct : BaseAct() {

    override val layoutId = R.layout.act_approver_selection

    private val approverAdapter = ApproverAdapter()

    override fun initViews() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            approverAdapter.bindToRecyclerView(this)
        }
        HorizontalDividerItemDecoration.Builder(this)
                .color(ContextCompat.getColor(this, R.color.md_grey_300))
                .size(1)
                .build()
                .let { recyclerView.addItemDecoration(it) }
    }

    override fun bindIntent() {
        TestHelper.getApproverObservable()
//                .delay(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { flowNodeList ->
                    val list = ArrayList<CheckedWrapper<Approver>>()
                    flowNodeList.forEach { list.add(CheckedWrapper(it)) }
                    return@map list
                }
                .subscribe { approverAdapter.setNewData(it) }
    }

}
