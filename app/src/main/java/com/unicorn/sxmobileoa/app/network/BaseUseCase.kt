package com.unicorn.sxmobileoa.app.network

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.content.Intent
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.common
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.model.Response
import com.unicorn.sxmobileoa.app.network.model.SimpleResponse
import com.unicorn.sxmobileoa.app.network.model.request
import com.unicorn.sxmobileoa.login.ui.LoginAct
import io.reactivex.Maybe
import okhttp3.MediaType
import okhttp3.RequestBody
import org.simpleframework.xml.core.Persister
import java.io.StringWriter

abstract class BaseUseCase<Result> {

    protected lateinit var request: request

    abstract fun toResult(json: String): Result

    fun toMaybe(lifecycleOwner: LifecycleOwner): Maybe<Result> {
        val requestXml = toXml(request)
        val requestBody = RequestBody.create(MediaType.parse("text/xml"), requestXml)
        return ComponentHolder.appComponent.getUniqueApi().post(requestBody)
                .map{
                    return@map toSimpleResponse(it)
                }
                .filter {
                    simpleResponse ->
                    val success = simpleResponse.code == Key.SUCCESS_CODE
                    if (!success) ToastUtils.showShort(simpleResponse.msg)
                    return@filter success
                }
                .filter {
                    if (it.message == 2) {
                        val context = lifecycleOwner as Context
                        ToastUtils.showShort("登录超时")
                        context.startActivity(Intent(context, LoginAct::class.java))
                    }
                    it.message != 2
                }
                .map { it.result!! }
                .common(lifecycleOwner)
    }

    private fun toXml(source: Any) = StringWriter().apply { Persister().write(source, this) }.toString()

    private fun toSimpleResponse(response: Response) = SimpleResponse<Result>(response.code, response.msg).apply {
        response.parameters?.parameterList?.forEach { parameter ->
            when (parameter.name) {
                "key" -> Global.ticket = parameter.text
                "message" -> message = parameter.text.toInt()
                "result" -> result = toResult(parameter.text)
            }
        }
    }

}