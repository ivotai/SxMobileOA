package com.unicorn.sxmobileoa

import android.app.Application
import com.blankj.utilcode.util.Utils
import com.facebook.stetho.Stetho
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

class App :Application(){

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init(){
        Logger.addLogAdapter(AndroidLogAdapter())
        Utils.init(this)
        Stetho.initializeWithDefaults(this)
    }

}