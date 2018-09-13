package com.unicorn.sxmobileoa.spdNext

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
import com.unicorn.sxmobileoa.spdNext.model.User
import com.unicorn.sxmobileoa.spdNext.network.user.GetUser
import com.unicorn.sxmobileoa.spdNext.ui.UserAdapter
import kotlinx.android.synthetic.main.act_spd_next.*

class SpdNextAct : BaseAct() {

    override val layoutId = R.layout.act_spd_next

    lateinit var menu: Menu

    lateinit var dbxx: Dbxx
    lateinit var spd: Spd

    override fun initArguments() {
        menu = intent.getSerializableExtra(Key.menu) as Menu
        dbxx = intent.getSerializableExtra(Key.dbxx) as Dbxx
        spd = intent.getSerializableExtra(Key.spd) as Spd
    }


    val adapter1 = Adapter1()
    val userAdapter = UserAdapter()

    override fun initViews() {
        recyclerView1.apply {
            layoutManager = LinearLayoutManager(this@SpdNextAct)
            adapter1.bindToRecyclerView(this)
            addDefaultItemDecoration()
        }
        recyclerView2.apply {
            layoutManager = LinearLayoutManager(this@SpdNextAct)
            userAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration()
            addHeaderView()
        }
    }

    private lateinit var headerView: KeywordHeaderView

    private fun addHeaderView() {
        headerView = KeywordHeaderView(this)
        headerView.setHint("请输入姓名")
        userAdapter.addHeaderView(headerView)
    }

    override fun bindIntent() {
        adapter1.setNewData(listOf(1, 2, 3, 5, 6, 7, 8))

//        SpdNext(menu,dbxx,spd).toMaybe(this).subscribe {
//        }

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
                .doOnSuccess { observeKeyword(it) }
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
