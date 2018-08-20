package com.unicorn.sxmobileoa.app.chongqing

import cn.net.withub.common.util.sec.Md5Main
import com.google.gson.Gson
import com.unicorn.sxmobileoa.app.Global
import org.joda.time.DateTime
import java.util.*

class Params(val busiCode: String,
             val parameters: HashMap<String, Any>) {

    val uuid = "776ef4e7-0edd-467b-9b4c-c33ec3fb4d8a"
    val version = "1.0"
    // 请求业务
    val loginName = ""
    val appId = "1c7bd52c-1bfe-11e6-b6ba-3e1d05defe78"
    // 64位以内流水号每次访问是唯一的
    var thirdFlow = UUID.randomUUID().toString()
    // 登陆后的返回值用来获取其它参数
    var ticket = Global.LOGIN_PARAMETERS?.ticket
    // 随机数
    var randCode = Md5Main.getRandom()
    // 时间
    var time = DateTime().toString(Key.DATE_VALUE_FORMAT)
    var seqM = ""
    // md5加密后的字符串
    var secM = Md5Main.sign(uuid + busiCode + thirdFlow + appId + randCode + md5key)

    // TODO 依赖注入 Gson 或尝试 data class
    override fun toString(): String {
        return Gson().toJson(this)
    }

    companion object {
        // md5密钥
        val md5key = "8d021e610b57b6f1"
    }

}
