package com.unicorn.sxmobileoa.spdNext.ui

import android.support.v7.widget.LinearLayoutManager
import com.orhanobut.logger.Logger
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.addDefaultItemDecoration
import com.unicorn.sxmobileoa.app.mess.KeywordHeaderView
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.mess.SpdHelper
import com.unicorn.sxmobileoa.app.mess.model.SelectWrapper
import com.unicorn.sxmobileoa.app.mess.model.UserResult
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.textChanges
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.spdNext.model.NextTaskSequenceFlow
import com.unicorn.sxmobileoa.spdNext.model.User
import com.unicorn.sxmobileoa.spdNext.network.CommitTask
import com.unicorn.sxmobileoa.spdNext.network.nextUser.NextUser
import com.unicorn.sxmobileoa.spdNext.network.spdNext.SpdNext
import com.unicorn.sxmobileoa.spdNext.network.user.GetUser
import dart.DartModel
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.act_spd_next.*

class SpdNextAct : BaseAct() {

    private val sequenceFlowAdapter = NextTaskSequenceFlowAdapter()

    private val userAdapter = UserAdapter()

    override fun initViews() {
        titleBar.setTitle("选择审批流程")
        titleBar.setOperation("确认").safeClicks().subscribe { next() }
        flowRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@SpdNextAct)
            sequenceFlowAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration()
        }
        userRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@SpdNextAct)
            userAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration()
        }
    }

    private lateinit var headerView: KeywordHeaderView

    private fun addHeaderView() {
        headerView = KeywordHeaderView(this)
        headerView.setHint("请输入姓名")
        userAdapter.addHeaderView(headerView)
    }

    private fun next() {
        val list = sequenceFlowAdapter.data.filter { it.isSelected }.map { it.t }
        if (list.isEmpty()) return

        val list2 = userAdapter.data.filter { it.isSelected }.map { it.t }
        if (list2.isEmpty()) return

        val result = UserResult(
                userIds = list2.joinToString(",") { it.id },
                userNames = list2.joinToString(",") { it.userFullName }
        )
        val taskInstance = SpdHelper().buildSpdNextParam(model.spd, model.saveSpdResponse, list[0], result)
        CommitTask(taskInstance).toMaybe(this).subscribe {
            Logger.e(it.toString())
        }
    }

    override fun bindIntent() {
        getSptNext()
        getUser()
    }

    private fun getSptNext() {
        SpdNext(model.menu, model.dbxx, model.spd).toMaybe(this)
                .map { it.nextTask_sequenceFlow }
                .map { it.map { sequenceFlow -> SelectWrapper(sequenceFlow) } }
                .subscribe { sequenceFlowAdapter.setNewData(it) }
    }

    private fun getUser() {
        GetUser().toMaybe(this)
                .map {
                    ArrayList<User>().apply {
                        it[0].children.forEach { deptTree ->
                            // TODO
                            deptTree.children.forEach { user ->
                                if (user.userFullName != null)
                                    add(user)
                            }
                        }
                    }
                }
                .doOnSuccess {
                    addHeaderView()
                    observeKeyword(it)
                }
                .map { userList ->
                    ArrayList<SelectWrapper<User>>().apply {
                        userList.forEach { user -> this.add(SelectWrapper(user)) }
                    }
                }
                .subscribe {
                    userAdapter.setNewData(it)
                }
    }

    private fun observeKeyword(userList: List<User>) {
        headerView.etKeyword.textChanges().subscribe { keyword ->
            userList.filter { user -> user.text.contains(keyword) }
                    .map { user -> SelectWrapper(user) }
                    .let { userAdapter.setNewData(it) }
        }
    }

    override fun registerEvent() {
        // TODO dealperson  == 1  时  结束节点无需选择人员
        RxBus.get().registerEvent(NextTaskSequenceFlow::class.java, this, Consumer {
            NextUser(model.spd, it).toMaybe(this@SpdNextAct).subscribe {
                Logger.e(it.toString())
            }
        })
    }

    override val layoutId = R.layout.act_spd_next

    @DartModel
    lateinit var model: SpdNextActNavigationModel


}
