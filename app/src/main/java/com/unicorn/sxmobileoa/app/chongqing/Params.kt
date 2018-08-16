package com.unicorn.sxmobileoa.app.chongqing

import cn.net.withub.common.util.sec.Md5Main
import com.google.gson.Gson
import com.unicorn.sxmobileoa.app.Global
import org.joda.time.DateTime
import java.util.*

class Params(val busiCode: String,
             val parameters: HashMap<String, Any>) {

    val uuid = "58e01a1c-56e6-42f2-8725-563dca4b3898"
    val version = "1.0"
    // 请求业务
    val loginName = ""
    val appId = "04e8310b-86f0-478c-b102-76f3c0710c00"
    // 64位以内流水号每次访问是唯一的
    var thirdFlow = UUID.randomUUID().toString()
    // 登陆后的返回值用来获取其它参数
    var ticket = Global.loginResponse?.ticket
    // 随机数
    var randCode = Md5Main.getRandom()
    // 时间
    var time =                 DateTime().toString(Key.DATE_VALUE_FORMAT)

    var seqM =""
    // md5加密后的字符串
    var secM =  Md5Main.sign(uuid + busiCode + thirdFlow + appId + randCode + md5key)

    //

    override fun toString(): String {
        return Gson().toJson(this)
    }

    companion object {
        // md5密钥
        val md5key = "b6474df096ac3750"
    }

}
