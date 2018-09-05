package com.unicorn.sxmobileoa.login

import android.util.Xml
import com.blankj.utilcode.util.AppUtils
import com.orhanobut.logger.Logger
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.login.parse.Response
import com.unicorn.sxmobileoa.login.useCase.SimpleResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.RequestBody
import org.joda.time.DateTime
import org.xml.sax.InputSource
import org.xmlpull.v1.XmlSerializer
import java.io.StringReader
import java.io.StringWriter
import javax.xml.parsers.SAXParserFactory

abstract class BaseUseCase<Model> {

    abstract val busiCode: String

    abstract fun addParameters()

    private lateinit var serializer: XmlSerializer

    fun buildXml(): String {
        serializer = Xml.newSerializer()
        val stringWriter = StringWriter()
        serializer.apply {
            setOutput(stringWriter)
            startDocument("UTF-8", true)

            // request start
            startTag("", Key.request)

            val dateFormat = "yyyyMMddHHmmss"
            addTag("requestFlow", DateTime().toString(dateFormat) + RandomGeneter.generateString(6))
            addTag("version", AppUtils.getAppVersionName())
            addTag("UUID", "")
            addTag("busiCode", busiCode)
            addTag("loginName", Global.loginInfo?.loginName ?: "")
            addTag("loginBusiType", Global.loginInfo?.loginBusiType ?: "")
            addTag("ticket", Global.ticket ?: "")
            addTag("randCode", RandomGeneter.generateString(12))
            addTag("randCodeSec", "")
            addTag("time", DateTime().toString(dateFormat))
            addTag("phoneType", "android")

            // Parameters start
            startTag("", Key.parameters)

            // 添加自定义参数
            addParameters()

            // TODO COURT
            addParameter(Key.fydm, "R00")

            // Parameters end
            endTag("", Key.parameters)

            // request end
            endTag("", Key.request)
            endDocument()
            Logger.e(stringWriter.toString())
        }

        return stringWriter.toString()
    }

    fun start(): Single<SimpleResponse<Model>> {
        val api = ComponentHolder.appComponent.getGeneralApi()
        val xml = buildXml()
        val requestBody = RequestBody.create(MediaType.parse("text/xml"), xml)
        return api.post(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(this::map)
    }

    abstract fun map(response: Response): SimpleResponse<Model>

    fun parseXml(xml: String) {
        val saxParser = SAXParserFactory.newInstance().newSAXParser()
        val xmlReader = saxParser.xmlReader
//        reader.contentHandler =

        val stringReader = StringReader(xml)
        xmlReader.parse(InputSource(stringReader))


    }

    private fun addTag(name: String, text: String) {
        serializer.apply {
            startTag("", name)
            text(text)
            endTag("", name)
        }
    }

    fun addParameter(name: String, value: String) {
        serializer.apply {
            startTag("", Key.parameter)
            attribute("", "name", name)
            cdsect(value)
            endTag("", Key.parameter)
        }
    }


}