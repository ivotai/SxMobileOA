package com.unicorn.sxmobileoa.spdNext.ui

import android.support.v7.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.widget.RxTextView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.addDefaultItemDecoration
import com.unicorn.sxmobileoa.app.mess.KeywordHeaderView
import com.unicorn.sxmobileoa.app.mess.SelectWrapper
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.model.Spd
import com.unicorn.sxmobileoa.spdNext.model.NextTaskSequenceFlow
import com.unicorn.sxmobileoa.spdNext.model.User
import com.unicorn.sxmobileoa.spdNext.network.SpdNext
import com.unicorn.sxmobileoa.spdNext.network.user.GetUser
import dart.BindExtra
import dart.DartModel
import kotlinx.android.synthetic.main.act_spd_next.*

@DartModel
class SpdNextAct : BaseAct() {

    override val layoutId = R.layout.act_spd_next

    @BindExtra(Key.menu)
    lateinit var menu: Menu

    lateinit var dbxx: Dbxx
    lateinit var spd: Spd

    override fun initArguments() {
        dbxx = intent.getSerializableExtra(Key.dbxx) as Dbxx
        spd = intent.getSerializableExtra(Key.spd) as Spd
    }


    private val nextTaskAdapter = NextTaskAdapter()
    private val userAdapter = UserAdapter()

    override fun initViews() {
        titleBar.setTitle("选择审批流程")
        titleBar.setOperation("确认")
        recyclerView1.apply {
            layoutManager = LinearLayoutManager(this@SpdNextAct)
            nextTaskAdapter.bindToRecyclerView(this)
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

        SpdNext(menu, dbxx, spd).toMaybe(this)
                .map {
                    ArrayList<SelectWrapper<NextTaskSequenceFlow>>().apply {
                        it.nextTask_sequenceFlow.forEach { nextTaskSequenceFlow ->
                            add(SelectWrapper(nextTaskSequenceFlow))
                        }
                    }
                }
                .subscribe {
                    nextTaskAdapter.setNewData(it)
                }
    }


    private fun getUser() {
        GetUser().toMaybe(this)
                .map {
                    ArrayList<User>().apply {
                        it[0].children.forEach { deptTree ->
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


}
