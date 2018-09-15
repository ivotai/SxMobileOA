package com.unicorn.sxmobileoa.commitTask.ui

import android.content.Intent
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.spdNext.ui.SequenceFlowAct
import dart.DartModel
import kotlinx.android.synthetic.main.act_commit_task.*

class CommitTaskAct : BaseAct() {

    override val layoutId = R.layout.act_commit_task

    override fun initViews() {
        titleBar.setTitle("选择审批流程")
    }

    override fun bindIntent() {
        tvSequenceFlow.safeClicks().subscribe {
            startActivity(Intent(this@CommitTaskAct, SequenceFlowAct::class.java).apply {
                putExtra(Key.menu, model.menu)
                putExtra(Key.dbxx, model.dbxx)
                putExtra(Key.spd, model.spd)
            })
        }
    }

    @DartModel
    lateinit var model: CommitTaskActNavigationModel

}