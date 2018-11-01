package com.unicorn.sxmobileoa.spd.ui.headerView

import android.annotation.SuppressLint
import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.blankj.utilcode.util.ToastUtils
import com.orhanobut.logger.Logger
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.config.ConfigModule
import com.unicorn.sxmobileoa.app.mess.FileUtils2
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.n.attachment.AttachmentAct
import com.unicorn.sxmobileoa.spd.network.spdZw.SpdZw
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.FileCallBack
import okhttp3.Call
import java.io.File


@SuppressLint("CheckResult")
class OperationHeaderView(context: Context, val spdId: String) : FrameLayout(context) {

    init {
        initViews(context)
    }

    fun initViews(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.header_view_operation, this, true)
        llZhengWen = view.findViewById(R.id.llZhengWen)
        llZhengWen.safeClicks().subscribe { _ ->
            SpdZw(spdId).toMaybe(context as LifecycleOwner).subscribe {
                if (TextUtils.isEmpty(it.wjdz)) {
                    ToastUtils.showShort("无正文")
                    return@subscribe
                }

                Logger.e(it.toString())
                val dz = it.wjdz
                val lastIndex = dz.lastIndexOf("/")
                val fileName = dz.substring(lastIndex + 1, dz.length)
                val file = File(ConfigModule.baseDir(), fileName)
                if (file.exists()) {
                    FileUtils2.openFile(context, file = file)
                } else {
                    OkHttpUtils
                            .get()
                            .url(it.wjdz)
                            .build()
                            .execute(object : FileCallBack(ConfigModule.baseDir(), fileName) {
                                override fun onResponse(response: File, id: Int) {
                                    FileUtils2.openFile(context, file = response)
                                }

                                override fun onError(call: Call?, e: Exception?, id: Int) {
                                    ToastUtils.showShort("SHIBAI")
                                }
                            })
                }
            }
        }

        val llAttachment = findViewById<LinearLayout>(R.id.llAttachment)
        llAttachment.safeClicks().subscribe { _ ->
            context.startActivity(Intent(context, AttachmentAct::class.java))
        }
    }


    private fun openFile(file: File) {

    }

    lateinit var llZhengWen: LinearLayout

}