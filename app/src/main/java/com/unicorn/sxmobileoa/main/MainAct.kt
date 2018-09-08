package com.unicorn.sxmobileoa.main

import android.support.design.widget.TabLayout.MODE_SCROLLABLE
import android.support.v7.widget.GridLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.main.data.Faker
import com.unicorn.sxmobileoa.main.ui.HeaderView
import com.unicorn.sxmobileoa.main.ui.MainAdapter
import io.reactivex.Observable
import kotlinx.android.synthetic.main.act_main.*



class MainAct : BaseAct() {

    override val layoutId = R.layout.act_main

    override fun initViews() {
        titleBar.setTitle(Global.court!!.dmms)
        intSlidingTabLayout()
        initRecyclerView()
    }

    private fun intSlidingTabLayout() {
        tabLayout.tabMode = MODE_SCROLLABLE

        Faker().getMainSection()
                .toObservable()
                .flatMap { Observable.fromIterable(it) }
                .filter { it.isHeader }
                .map { it.header }
                .subscribe{ it->
                    val tab = tabLayout.newTab()
                    tab.setText(it)
                    tabLayout.addTab(tab)

                }
//        val list =ArrayList<String>()
//        tab.setTitles(arrayOf("",""))
//
//        addNumbers(*intArrayOf(1,2))
    }


    fun addNumbers( vararg args: Int): Int {
        var result = 0
        for (i in args.indices) {
            result += args[i]
        }
        return result
    }


    private val mainAdapter = MainAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainAct, 4)
            mainAdapter.bindToRecyclerView(this)
            mainAdapter.addHeaderView(HeaderView(this@MainAct))
        }
    }

    override fun bindIntent() {
        Faker().getMainSection().subscribe {
            mainAdapter.setNewData(it)
        }
    }

}
