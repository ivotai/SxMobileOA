package com.unicorn.sxmobileoa.app.network

import android.arch.lifecycle.LifecycleOwner
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.model.Response
import com.unicorn.sxmobileoa.app.network.model.SimpleResponse
import com.unicorn.sxmobileoa.app.network.model.request
import com.unicorn.sxmobileoa.app.utils.MainThreadTransformer
import florent37.github.com.rxlifecycle.RxLifecycle
import io.reactivex.Maybe
import okhttp3.MediaType
import okhttp3.RequestBody
import org.simpleframework.xml.core.Persister
import java.io.StringWriter

abstract class BaseUseCase<Model> {

    abstract fun createRequest(): request

    abstract fun toModel(json: String): Model

    fun toMaybe(lifecycleOwner: LifecycleOwner): Maybe<Model> {
        val requestXml = buildRequestXml()
        val requestBody = RequestBody.create(MediaType.parse("text/xml"), requestXml)
        return ComponentHolder.appComponent.getGeneralApi().post(requestBody)
                .map(this::toSimpleResponse)
                .filter { response ->
                    val success = response.code == Key.SUCCESS_CODE
                    if (!success) ToastUtils.showShort(response.msg)
                    return@filter success
                }
                .map { it.result }
                .compose(MainThreadTransformer())
                .compose(RxLifecycle.disposeOnDestroy(lifecycleOwner))
    }

    private fun buildRequestXml(): String {
        val stringWriter = StringWriter()
        Persister().write(createRequest(), stringWriter)
        return stringWriter.toString()
    }

    private fun toSimpleResponse(original: Response) = SimpleResponse<Model>(original.code, original.msg).apply {
        original.parameters?.parameterList?.forEach { parameter ->
            when (parameter.name) {
                "key" -> Global.ticket = parameter.text
                "message" -> message = parameter.text
                "result" -> result = toModel(parameter.text)
            }
        }
    }

}