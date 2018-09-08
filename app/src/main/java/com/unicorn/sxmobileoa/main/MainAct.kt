package com.unicorn.sxmobileoa.main

import android.content.Context
import android.support.design.widget.TabLayout
import android.support.design.widget.TabLayout.MODE_SCROLLABLE
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.FrameLayout
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.ScreenUtils
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.main.data.Faker
import com.unicorn.sxmobileoa.main.ui.MainAdapter
import kotlinx.android.synthetic.main.act_main.*




class MainAct : BaseAct() {

    override val layoutId = R.layout.act_main
var lastH= 0
    //判读是否是recyclerView主动引起的滑动，true- 是，false- 否，由tablayout引起的
//    private var isRecyclerScroll: Boolean = false
    //记录上一次位置，防止在同一内容块里滑动 重复定位到tablayout
    private var lastPos: Int = 0
    //用于recyclerView滑动到指定的位置

    var click = false


    fun getStatusBarHeight(context: Context): Int {
        var result = 0
        val resourceId = context.resources
                .getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    override fun initViews() {



        titleBar.setTitle(Global.court!!.dmms)
        intSlidingTabLayout()
        initRecyclerView()

        val screenH = ScreenUtils.getScreenHeight()
        val statusBarH = getStatusBarHeight(this)
        val titleBar = ConvertUtils.dp2px(56f)
        val tab = ConvertUtils.dp2px(56f)
        val items = ConvertUtils.dp2px(48.toFloat() + 64.toFloat())
         lastH = screenH - statusBarH - titleBar-tab-items
        ToastUtils.showShort(lastH.toString())
    }

    private lateinit var manager: GridLayoutManager


    private fun f1(posTab:Int):Int{
        var position = 0
        val text =tabLayout.getTabAt(posTab)!!.text.toString()
        Faker().getMainSection().map { it.header }.toList()
                .subscribe { list ->

                    for (it in list){
                        if (it == text){
                            position =list.indexOf(it)
                            break
                        }
                    }
                }
        return position
    }

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
                click =true
                val pos = tab.position
//                isRecyclerScroll = false



                val pos2 = f1(pos)
                ToastUtils.showShort(pos2.toString())
                moveToPosition(manager, recyclerView, pos2)


        }})
    }

    //用于recyclerView滑动到指定的位置
    private var canScroll: Boolean = false
    private var scrollToPosition: Int = 0

    fun moveToPosition(manager: GridLayoutManager, mRecyclerView: RecyclerView, position: Int) {
        // 第一个可见的view的位置
        val firstItem = manager.findFirstVisibleItemPosition()
        // 最后一个可见的view的位置
        val lastItem = manager.findLastVisibleItemPosition()
        if (position <= firstItem) {
            // 如果跳转位置firstItem 之前(滑出屏幕的情况)，就smoothScrollToPosition可以直接跳转，
            mRecyclerView.smoothScrollToPosition(position)
        } else if (position <= lastItem) {
            // 跳转位置在firstItem 之后，lastItem 之间（显示在当前屏幕），smoothScrollBy来滑动到指定位置
            val top = mRecyclerView.getChildAt(position - firstItem).top
            mRecyclerView.smoothScrollBy(0, top)
        } else {
            // 如果要跳转的位置在lastItem 之后，则先调用smoothScrollToPosition将要跳转的位置滚动到可见位置
            // 再通过onScrollStateChanged控制再次调用当前moveToPosition方法，执行上一个判断中的方法
            mRecyclerView.smoothScrollToPosition(position)
            scrollToPosition = position
            canScroll = true
        }
    }
    private val mainAdapter = MainAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            manager = GridLayoutManager(this@MainAct, 4)
            layoutManager = manager
            mainAdapter.bindToRecyclerView(this)
//            mainAdapter.addHeaderView(HeaderView(this@MainAct))
        }

        val frameLayout = FrameLayout(this)
        val root =FrameLayout(this)

        val params = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, lastH)
        frameLayout.addView(root,params)
//        params.height = lastH
//        headerView.root.setLayoutParams(params)

        mainAdapter.addFooterView(frameLayout)
//
//        HorizontalDividerItemDecoration.Builder(this)
//                .colorResId(R.color.md_red_400)
//                .size(1).build().let { recyclerView.addItemDecoration(it) }

//        recyclerView.setOnTouchListener { v, event ->
//            //当滑动由recyclerView触发时，isRecyclerScroll 置true
//            if (event.action == MotionEvent.ACTION_DOWN) {
//                isRecyclerScroll = true
//            }
//            false
//        }




        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            if (click){click=false
            return}
                    //第一个可见的view的位置，即tablayou需定位的位置
                    val position = manager.findFirstVisibleItemPosition()
                    if (lastPos != position) {
                        tabLayout.setScrollPosition(f2(position), 0f, true)
                    }
                    lastPos = position

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (canScroll) {
                    canScroll = false
                    moveToPosition(manager, recyclerView, scrollToPosition)
                }
            }
        })
    }

    fun f2(firstPos:Int):Int{
        var position = 0
       val header = mainAdapter.data[firstPos].header
        Faker().getMainSection().filter { it.isHeader }.map { it.header }.toList()
                .subscribe {list ->list.forEachIndexed { index, it ->
                    if (it == header){
                        position = list.indexOf(it)
                    }
                }

                }
        return position
    }

    override fun bindIntent() {
        Faker().getMainSection().toList()
                .subscribe { t ->
                    mainAdapter.setNewData(t)
                }
    }

}
