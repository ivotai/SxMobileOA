package com.unicorn.sxmobileoa.commitTask.ui

import android.content.Intent
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.mess.SpdHelper
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.commitTask.network.CommitTask
import com.unicorn.sxmobileoa.spdNext.model.SequenceFlowResult
import com.unicorn.sxmobileoa.spdNext.ui.SequenceFlowAct
import dart.DartModel
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.act_commit_task.*

class CommitTaskAct : BaseAct() {

    override val layoutId = R.layout.act_commit_task

    override fun initViews() {
        titleBar.setTitle("选择审批流程")
        titleBar.setOperation("确定").safeClicks().subscribe {
            if (flowResult == null) return@subscribe
            val instance = SpdHelper().buildTaskInstance(model.spd, model.saveSpdResponse, flowResult.sequenceFlow, flowResult.userList)
            CommitTask(instance).toMaybe(this).subscribe {
                com.orhanobut.logger.Logger.e(it.toString())
            }
        }
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

    lateinit var flowResult: SequenceFlowResult

    override fun registerEvent() {
        RxBus.get().registerEvent(SequenceFlowResult::class.java, this, Consumer {
            flowResult = it
            tvSequenceFlow.text = it.sequenceFlow.nextTaskShowName
            tvUsers.text = it.userList.joinToString(",") { it.fullname }
        })
    }

    @DartModel
    lateinit var model: CommitTaskActNavigationModel

}