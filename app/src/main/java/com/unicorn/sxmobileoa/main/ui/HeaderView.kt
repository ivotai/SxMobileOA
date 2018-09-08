package com.unicorn.sxmobileoa.main.ui

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.unicorn.sxmobileoa.R

class HeaderView(context: Context) : FrameLayout(context) {

    init {
        initViews(context)
    }

    lateinit var root: LinearLayout
    fun initViews(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.header_view_main, this, true)
//        initBanner()
        root = findViewById(R.id.root)
    }

    private fun initBanner() {
//        banner = findViewById(R.id.banner)
//        banner.setImageLoader(GlideImageLoader())
//                .setImages(listOf(
//                        "http://img0.imgtn.bdimg.com/it/u=907078664,584020426&fm=26&gp=0.jpg",
//                        "http://wenminghanzhong.cn/UserData/UEditor/2017-05-19/636308004298652343.jpg",
//                        "http://www.sxtvs.com/images/2009-01/01/xin_14010601130948480332.jpg"
//                ))
//                .start()
    }

}