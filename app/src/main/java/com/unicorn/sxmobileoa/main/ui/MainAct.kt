package com.unicorn.sxmobileoa.main.ui

import android.support.v7.widget.GridLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.main.model.Menu
import com.unicorn.sxmobileoa.main.model.Section
import com.unicorn.sxmobileoa.main.network.MainUseCase
import kotlinx.android.synthetic.main.act_main.*


class MainAct : BaseAct() {

    override val layoutId = R.layout.act_main

    override fun initViews() {
        titleBar.setTitle("陕西省高级人民法院移动办公")
        initRecyclerView()
    }

    private val mainAdapter = MainAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainAct, 4)
            mainAdapter.bindToRecyclerView(this)
        }
    }

    override fun bindIntent() {
        MainUseCase().toMaybe(this)
                .map { toSectionList(it) }
                .doOnSuccess {
                    it.forEach {
                        section -> section.t?.resId = R.mipmap.bangong_98
                    }
                }
                .subscribe {
                    mainAdapter.setNewData(it)
                }


//        DataProvider().getMainSection()
//                .toList()
//                .subscribe { t -> mainAdapter.setNewData(t) }
    }

    private fun toSectionList(menuList: List<Menu>): List<Section> {
        val sectionList = ArrayList<Section>()
        menuList.forEach { menu ->
            sectionList.add(Section(header = menu.name, isHeader = true))
            menu.mlxx.forEach {
                sectionList.add(Section(t = it))
            }
        }
        return sectionList
    }


}
