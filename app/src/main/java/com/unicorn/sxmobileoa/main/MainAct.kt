package com.unicorn.sxmobileoa.main

import android.support.design.widget.TabLayout
import android.support.design.widget.TabLayout.MODE_SCROLLABLE
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.main.data.Faker
import com.unicorn.sxmobileoa.main.ui.MainAdapter
import kotlinx.android.synthetic.main.act_main.*




class MainAct : BaseAct() {

    override val layoutId = R.layout.act_main

    override fun initViews() {
        titleBar.setTitle(Global.court!!.dmms)
        intSlidingTabLayout()
        initRecyclerView()
    }

    var click = false

    private fun intSlidingTabLayout() {
        tabLayout.tabMode = MODE_SCROLLABLE

        Faker().getMainSection()
                .filter { it.isHeader }
                .map { it.header }
                .subscribe { it ->
                    val tab = tabLayout.newTab()
                    tab.text = it
                    tabLayout.addTab(tab)

                }


        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }



            override fun onTabSelected(tab: TabLayout.Tab) {
                click = true
                val text = tab.text.toString()
                val header = text!!
                Faker().getMainSection()
                        .filter { !it.isHeader }
                        .map { it.header }
                        .toList()
                        .subscribe { list ->
                            list.forEachIndexed { index, it ->
                                if (it == header) {
                                    recyclerView.scrollToPosition(index)
                                }
                            }
                        }
//                recyclerView.childCount.let { Logger.e(it.toString()) }
//                val position = tab.getPosition();
//                val l = recyclerView.getLayoutManager() as GridLayoutManager
//                val firstPosition = l.findFirstVisibleItemPosition();
//                val lastPosition = l.findLastVisibleItemPosition();
//                if (position > lastPosition) {
//                    recyclerView.smoothScrollToPosition(position);
//                } else if (position < firstPosition) {
//                    recyclerView.smoothScrollToPosition(position);
//                } else {
//                    val top = recyclerView.getChildAt(position - firstPosition).getTop();
//                    recyclerView.smoothScrollBy(0, top);
//
//                }
        }})
//        val list =ArrayList<String>()
//        tab.setTitles(arrayOf("",""))
//
//        addNumbers(*intArrayOf(1,2))
    }



    private val mainAdapter = MainAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainAct, 4)
            mainAdapter.bindToRecyclerView(this)
//            mainAdapter.addHeaderView(HeaderView(this@MainAct))
        }
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (click) {click =false
                return}
                val l = recyclerView.getLayoutManager() as GridLayoutManager
                val firstPosition = l.findFirstVisibleItemPosition()
                mainAdapter.getItem(firstPosition)!!.header.let { ToastUtils.showShort(it) }
                val header = mainAdapter.getItem(firstPosition)!!.header
                Faker().getMainSection().filter { it.isHeader }.map { it.header }
                        .toList().subscribe { list ->
                            list.forEachIndexed { index, it ->
                                if (it == header){
                                    tabLayout.setScrollPosition(index,0f,true)
                                }
                            }
                        }

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

//                val lastPosition = l.findLastVisibleItemPosition()
//                val allItems = l.itemCount
//                tabLayout.setScrollPosition(firstPosition, 0f, true)

            }
        })
    }

    override fun bindIntent() {
        Faker().getMainSection().toList()
                .subscribe { t ->
                    mainAdapter.setNewData(t)
                }
    }

}
