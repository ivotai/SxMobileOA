package com.unicorn.sxmobileoa.app

import android.app.Application
import com.blankj.utilcode.util.Utils
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import net.danlew.android.joda.JodaTimeAndroid

class App :Application(){

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init(){
        JodaTimeAndroid.init(this)
        Logger.addLogAdapter(AndroidLogAdapter())
        Utils.init(this)
    }

}