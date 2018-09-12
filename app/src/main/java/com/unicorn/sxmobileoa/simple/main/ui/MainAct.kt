package com.unicorn.sxmobileoa.simple.main.ui

import android.support.v7.widget.GridLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.simple.main.model.ParentMenu
import com.unicorn.sxmobileoa.simple.main.model.section.MenuSection
import com.unicorn.sxmobileoa.simple.main.network.GetMenu
import kotlinx.android.synthetic.main.act_titlebar_recyclerview.*

class MainAct : BaseAct() {

    override val layoutId = R.layout.act_titlebar_recyclerview

    override fun initViews() {
        titleBar.setTitle("陕西省高级人民法院移动办公")
        initRecyclerView()
    }

    private val menuAdapter = MenuAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainAct, 4)
            menuAdapter.bindToRecyclerView(this)
        }
    }

    // 硬编码 TODO
    private val resIds = listOf(
            R.mipmap.fawen, R.mipmap.shouwen,
            R.mipmap.bangong_98, R.mipmap.bangong_98, R.mipmap.bangong_98,
            R.mipmap.yongche, R.mipmap.bangong_98,
            R.mipmap.bangong_98, R.mipmap.bangong_98,
            R.mipmap.bangong_98,
            R.mipmap.bangong_98, R.mipmap.bangong_98, R.mipmap.bangong_98
    )

    override fun bindIntent() {
        GetMenu().toMaybe(this)
                .map { toSectionList(it) }
                .doOnSuccess { sectionList ->
                    sectionList.filter { !it.isHeader }
                            .forEachIndexed { index, section ->
                                section.t?.resId = resIds[index]
                            }
                }
                .subscribe { menuAdapter.setNewData(it) }
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

}
