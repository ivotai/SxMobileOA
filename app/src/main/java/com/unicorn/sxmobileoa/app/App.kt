package com.unicorn.sxmobileoa.app

import android.app.Application
import com.blankj.utilcode.util.ToastUtils
import com.blankj.utilcode.util.Utils
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import io.reactivex.plugins.RxJavaPlugins
import net.danlew.android.joda.JodaTimeAndroid
import java.net.SocketTimeoutException

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
        setErrorHandler()
    }

    private fun init() {
        JodaTimeAndroid.init(this)
        Logger.addLogAdapter(AndroidLogAdapter())
        Utils.init(this)
    }

    private fun setErrorHandler() {
        RxJavaPlugins.setErrorHandler {
            if (it is SocketTimeoutException || it.cause is SocketTimeoutException) {
                ToastUtils.showShort("连接超时")
            } else {
                ToastUtils.showShort(it.toString())
            }
        }
    }

}