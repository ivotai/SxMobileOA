package com.unicorn.sxmobileoa.login.useCase

import com.orhanobut.logger.Logger
import com.unicorn.sxmobileoa.login.BaseUseCase
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody


class LoginUseCase(private val username: String, private val password: String) : BaseUseCase() {

    override val busiCode = "oalogin"

    override fun addParameters() {
        addParameter("userName", username)
        addParameter("passWord", password)
    }

    fun start() {
        Thread {
            val url = "http://154.0.66.127:80/busiGate/request.shtml"
            val mediaType = MediaType.parse("text/xml")
            val xml  = buildXml()
            val body = RequestBody.create(mediaType, xml)
            val request = Request.Builder()
                    .url(url)
                    .post(body)
                    .build()
            val response = OkHttpClient().newCall(request).execute()
            Logger.e(response.body()!!.string())
        }.start()
    }


}