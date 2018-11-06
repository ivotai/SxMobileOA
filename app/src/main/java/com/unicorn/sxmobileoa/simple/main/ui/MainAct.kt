package com.unicorn.sxmobileoa.simple.main.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.commitTask.model.CommitTaskSuccess
import com.unicorn.sxmobileoa.login.ui.LoginAct
import com.unicorn.sxmobileoa.simple.main.model.ParentMenu
import com.unicorn.sxmobileoa.simple.main.model.section.MenuSection
import com.unicorn.sxmobileoa.simple.main.network.GetMenu
import com.unicorn.sxmobileoa.simple.main.network.loginout.LoginOut
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.act_main.*

@SuppressLint("CheckResult")
class MainAct : BaseAct() {

    override val layoutId = R.layout.act_main

    override fun initViews() {
        titleBar.setTitle("${Global.court!!.dmms}移动办公", true)
        initRecyclerView()
        nav_view.setNavigationItemSelectedListener { _ ->
            LoginOut().toMaybe(this).subscribe {
                drawer_layout.closeDrawers()
                startActivity(Intent(this@MainAct, LoginAct::class.java))
            }
            return@setNavigationItemSelectedListener true
        }
    }

    private val menuAdapter = MenuAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainAct, 4)
            menuAdapter.bindToRecyclerView(this)
        }
        swipeRefreshLayout.setColorScheme(R.color.colorPrimary)
        swipeRefreshLayout.setOnRefreshListener { getMenu() }
    }

    // 硬编码
    private val resIds = listOf(
            R.mipmap.fawen, R.mipmap.shouwen,
            R.mipmap.sbly, R.mipmap.sbbf, R.mipmap.sbwx,
            R.mipmap.yongche, R.mipmap.wply,
            R.mipmap.gcsq, R.mipmap.qjsq,
            R.mipmap.jdsq,
            R.mipmap.csx, R.mipmap.xwzx, R.mipmap.xxgg
    )

    override fun bindIntent() {
        getMenu()
    }

    private fun getMenu() {
        GetMenu().toMaybe(this)
                .map { toSectionList(it) }
                .doOnSuccess { sectionList ->
                    sectionList.filter { !it.isHeader }
                            .forEachIndexed { index, section ->
                                section.t?.resId = resIds[index]
                            }
                }
                .subscribe {
                    swipeRefreshLayout.isRefreshing = false
                    menuAdapter.setNewData(it)
                }
    }

    private fun toSectionList(parentMenuList: List<ParentMenu>): List<MenuSection> {
        val sectionList = ArrayList<MenuSection>()
        parentMenuList.forEach { parentMenu ->
            sectionList.add(MenuSection(header = parentMenu.name, isHeader = true))
            parentMenu.mlxx.forEach { menu ->
                sectionList.add(MenuSection(t = menu))
            }
        }
        return sectionList
    }

    override fun registerEvent() {
        RxBus.get().registerEvent(CommitTaskSuccess::class.java, this, Consumer {
            bindIntent()
        })
    }

}
