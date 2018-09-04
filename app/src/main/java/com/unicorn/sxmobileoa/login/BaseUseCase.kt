package com.unicorn.sxmobileoa.login

import android.util.Xml
import com.blankj.utilcode.util.AppUtils
import com.orhanobut.logger.Logger
import com.unicorn.sxmobileoa.app.Global
import org.joda.time.DateTime
import org.xml.sax.InputSource
import org.xmlpull.v1.XmlSerializer
import java.io.StringReader
import java.io.StringWriter
import java.util.*
import javax.xml.parsers.SAXParserFactory

abstract class BaseUseCase {

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
            addTag("UUID", UUID.randomUUID().toString())
            addTag("busiCode", busiCode)
            addTag("loginName", Global.loginInfo?.loginName ?: "")
            addTag("loginBusiType", Global.loginInfo?.loginBusiType ?: "")
            addTag("ticket", Global.ticket ?: "")
            addTag("randCode", RandomGeneter.generateString(12))
            addTag("randCodeSec", "")
            addTag("time", DateTime().toString(dateFormat))
            addTag("phoneType", "android")

            // parameters start
            startTag("", Key.parameters)

            // 添加自定义参数
            addParameters()

            // TODO COURT
            addParameter(Key.fydm, "R00")

            // parameters end
            endTag("", Key.parameters)

            // request end
            endTag("", Key.request)
            endDocument()
            Logger.e(stringWriter.toString())
        }

        return stringWriter.toString()
    }

    fun parseXml(xml:String){
        val saxParser  = SAXParserFactory.newInstance().newSAXParser()
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