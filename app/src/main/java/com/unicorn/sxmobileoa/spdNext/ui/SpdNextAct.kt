package com.unicorn.sxmobileoa.spdNext.ui

import android.support.v7.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.widget.RxTextView
import com.orhanobut.logger.Logger
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.addDefaultItemDecoration
import com.unicorn.sxmobileoa.app.mess.KeywordHeaderView
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.mess.SelectWrapper
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.spdNext.model.NextTaskSequenceFlow
import com.unicorn.sxmobileoa.spdNext.model.User
import com.unicorn.sxmobileoa.spdNext.network.nextUser.NextUser
import com.unicorn.sxmobileoa.spdNext.network.spdNext.SpdNext
import com.unicorn.sxmobileoa.spdNext.network.user.GetUser
import dart.DartModel
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.act_spd_next.*

class SpdNextAct : BaseAct() {

    override val layoutId = R.layout.act_spd_next

    @DartModel
    lateinit var model: SpdNextActNavigationModel

    private val sequenceFlowAdapter = NextTaskSequenceFlowAdapter()
    private val userAdapter = UserAdapter()

    override fun initViews() {
        titleBar.setTitle("选择审批流程")
        titleBar.setOperation("确认")
        recyclerView1.apply {
            layoutManager = LinearLayoutManager(this@SpdNextAct)
            sequenceFlowAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration()
        }
        recyclerView2.apply {
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

    override fun bindIntent() {

        getUser()

        SpdNext(model.menu, model.dbxx, model.spd).toMaybe(this)
                .map {
                    ArrayList<SelectWrapper<NextTaskSequenceFlow>>().apply {
                        it.nextTask_sequenceFlow.forEach { nextTaskSequenceFlow ->
                            add(SelectWrapper(nextTaskSequenceFlow))
                        }
                    }
                }
                .subscribe {
                    sequenceFlowAdapter.setNewData(it)
                }
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
        RxTextView.textChanges(headerView.etKeyword)
                .map { it.toString() }
                .subscribe { keyword ->
                    userList.filter { user -> user.text.contains(keyword) }
                            .map { user -> SelectWrapper(user) }
                            .let { userAdapter.setNewData(it) }
                }
    }

    override fun registerEvent() {
        RxBus.get().registerEvent(NextTaskSequenceFlow::class.java, this, Consumer {
            NextUser(model.spd, it).toMaybe(this@SpdNextAct).subscribe {
                Logger.e(it.toString())
            }
        })
    }

}
