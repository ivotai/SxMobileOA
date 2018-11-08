package com.unicorn.sxmobileoa.simple.main.ui

import android.annotation.SuppressLint
import android.support.v7.widget.GridLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.ui.BaseFra
import com.unicorn.sxmobileoa.commitTask.model.CommitTaskSuccess
import com.unicorn.sxmobileoa.simple.main.model.ParentMenu
import com.unicorn.sxmobileoa.simple.main.model.section.MenuSection
import com.unicorn.sxmobileoa.simple.main.network.GetMenu
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.act_main.*

@SuppressLint("CheckResult")
class HomeFra : BaseFra() {

    /*
            nav_view.setNavigationItemSelectedListener { _ ->
            LoginOut().toMaybe(this).subscribe {
                drawer_layout.closeDrawers()
                startActivity(Intent(this@HomeFra, LoginAct::class.java))
                finish()
            }
            return@setNavigationItemSelectedListener true
        }
     */

    override val layoutId = R.layout.act_main

    override fun initViews() {
        titleBar.setTitle("${Global.court!!.dmms}移动办公", true)
        initRecyclerView()
    }

    private val menuAdapter = MenuAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 4)
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
            R.mipmap.csx, R.mipmap.xwzx, R.mipmap.xxgg,
            R.mipmap.csx, R.mipmap.xwzx, R.mipmap.xxgg
    )


    override fun bindIntent() {
        getMenu()
    }

    private fun getMenu() {
        GetMenu().toMaybe(this)
//                .map { toSectionList(it) }
                .doOnSuccess { sectionList ->
                    sectionList.forEachIndexed { index, section ->
                                section.resId = resIds[index]
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
