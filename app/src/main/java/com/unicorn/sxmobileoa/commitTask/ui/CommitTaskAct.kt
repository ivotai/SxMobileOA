package com.unicorn.sxmobileoa.commitTask.ui

import android.content.Intent
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.mess.SpdHelper
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.commitTask.model.CommitTaskActNavigationModel
import com.unicorn.sxmobileoa.commitTask.model.CommitTaskSuccess
import com.unicorn.sxmobileoa.commitTask.network.CommitTask
import com.unicorn.sxmobileoa.sequenceFlow.model.SequenceFlowResult
import com.unicorn.sxmobileoa.sequenceFlow.ui.SequenceFlowAct
import dart.DartModel
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.act_commit_task.*

class CommitTaskAct : BaseAct() {

    private var sequenceFlowResult: SequenceFlowResult? = null

    override fun initViews() {
        titleBar.setTitle("选择审批流程")
    }

    override fun bindIntent() {
        clicks()
        setOperation()
    }

    private fun clicks() {
        tvSequenceFlow.safeClicks().mergeWith(tvUsers.safeClicks()).subscribe {
            startActivity(Intent(this@CommitTaskAct, SequenceFlowAct::class.java).apply {
                putExtra(Key.menu, model.menu)
                putExtra(Key.dbxx, model.dbxx)
                putExtra(Key.spd, model.spd)
            })
        }
    }


    override fun registerEvent() {
        RxBus.get().registerEvent(SequenceFlowResult::class.java, this, Consumer {
            sequenceFlowResult = it
            tvSequenceFlow.text = it.sequenceFlow.nextTaskShowName
            tvUsers.text = it.userList.joinToString(",") { user -> user.fullname }
        })
    }

    private fun setOperation() {
        titleBar.setOperation("确定").safeClicks().subscribe {
            val temp = sequenceFlowResult ?: return@subscribe
            val instance = SpdHelper().buildTaskInstance(model.spd, model.saveSpdResponse, temp.sequenceFlow, temp.userList)
            CommitTask(instance).toMaybe(this).subscribe { commitTaskResponse ->
                com.orhanobut.logger.Logger.e(commitTaskResponse.toString())
                ToastUtils.showShort("提交成功")
                RxBus.get().post(CommitTaskSuccess())
                finish()
            }
        }
    }

    @DartModel
    lateinit var model: CommitTaskActNavigationModel

    override val layoutId = R.layout.act_commit_task

}