package com.unicorn.sxmobileoa.app.network

import android.arch.lifecycle.LifecycleOwner
import android.util.Xml
import com.blankj.utilcode.util.AppUtils
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.model.Response
import com.unicorn.sxmobileoa.app.network.model.SimpleResponse
import com.unicorn.sxmobileoa.app.union.CommonTransformer
import com.unicorn.sxmobileoa.app.union.RandomGeneter
import florent37.github.com.rxlifecycle.RxLifecycle
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.RequestBody
import org.joda.time.DateTime
import org.xmlpull.v1.XmlSerializer
import java.io.StringWriter

abstract class BaseUseCase<Model> {

    abstract val busiCode: String

    abstract fun addParameters()

    // ============================ buildXml ============================

    private lateinit var serializer: XmlSerializer

    private fun buildXml(): String {
        val stringWriter = StringWriter()
        serializer = Xml.newSerializer()
        serializer.apply {
            setOutput(stringWriter)

            startDocument("UTF-8", true)
            startTag("", Key.request)

            addTag("requestFlow", DateTime().toString(Key.DATE_FORMAT) + RandomGeneter.generateString(6))
            addTag("version", AppUtils.getAppVersionName())
            addTag("UUID", "")
            addTag("busiCode", busiCode)
            addTag("loginName", Global.loginInfo?.loginName ?: "")
            addTag("loginBusiType", Global.loginInfo?.loginBusiType ?: "")
            addTag("ticket", Global.ticket ?: "")
            addTag("randCode", RandomGeneter.generateString(12))
            addTag("randCodeSec", "")
            addTag("time", DateTime().toString(Key.DATE_FORMAT))
            addTag("phoneType", "android")

            // parameters toSingle
            startTag("", Key.parameters)

            // 添加自定义参数
            addParameters()

//             TODO 获取用户选择的法院编码
//            addParameter(Key.fydm, "R00")

            // parameters end
            endTag("", Key.parameters)

            endTag("", Key.request)
            endDocument()
        }
        return stringWriter.toString()
    }

    private fun addTag(name: String, text: String) {
        serializer.apply {
            startTag("", name)
            text(text)
            endTag("", name)
        }
    }

    protected fun addParameter(name: String, value: String) {
        serializer.apply {
            startTag("", Key.parameter)
            attribute("", "name", name)
            cdsect(value)
            endTag("", Key.parameter)
        }
    }

    // ============================ toSingle ============================

    fun toSingle(lifecycleOwner: LifecycleOwner): Single<SimpleResponse<Model>> {
        val xml = buildXml()
        val requestBody = RequestBody.create(MediaType.parse("text/xml"), xml)
        return ComponentHolder.appComponent.getGeneralApi().post(requestBody)
                .compose(CommonTransformer())
                .compose(RxLifecycle.disposeOnDestroy(lifecycleOwner))
                .map(this::toSimpleResponse)
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

    abstract fun toModel(json: String): Model
//    {
//        val gson = ComponentHolder.appComponent.getGson()
//        val token = object : TypeToken<Model>() {
//
//        }.type
//        val t = gson.fromJson<Model>(result, token)
//        return t
//    }

}