package com.unicorn.sxmobileoa.login.useCase

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.login.BaseUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.RequestBody


class LoginUseCase(private val username: String, private val password: String) : BaseUseCase() {

    override val busiCode = "oalogin"

    override fun addParameters() {
        addParameter("userName", username)
        addParameter("passWord", password)
    }

    fun start() {
        val api = ComponentHolder.appComponent.getGeneralApi()
        val xml = buildXml()
        val requestBody = RequestBody.create(MediaType.parse("text/xml"),xml)
        api.post(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { com.orhanobut.logger.Logger.e(it.string()) }

//        Thread {
//            val url = "http://154.0.66.127:80/busiGate/request.shtml"
//            val mediaType = MediaType.parse("text/xml")
//            val xml  = buildXml()
//            val body = RequestBody.create(mediaType, xml)
//            val request = Request.Builder()
//                    .url(url)
//                    .post(body)
//                    .build()
//            val response = OkHttpClient().newCall(request).execute()
//            Logger.e(response.body()!!.string())
//        }.start()
    }


}