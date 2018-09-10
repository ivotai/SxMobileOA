package com.unicorn.sxmobileoa.app

import android.app.Application
import com.blankj.utilcode.util.ToastUtils
import com.blankj.utilcode.util.Utils
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.zzhoujay.richtext.RichText
import io.reactivex.plugins.RxJavaPlugins
import net.danlew.android.joda.JodaTimeAndroid
import java.net.SocketTimeoutException

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
        setGlobalErrorHandler()
    }

    private fun init() {
        JodaTimeAndroid.init(this)
        Logger.addLogAdapter(AndroidLogAdapter())
        Utils.init(this)
        RichText.initCacheDir(this)
    }

    private fun setGlobalErrorHandler() {
        RxJavaPlugins.setErrorHandler {
            if (it is SocketTimeoutException || it.cause is SocketTimeoutException) {
                ToastUtils.showShort("连接超时")
            } else {
                Logger.e(it.toString())
            }
        }
    }

}