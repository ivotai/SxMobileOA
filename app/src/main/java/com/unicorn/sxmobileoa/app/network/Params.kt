package com.unicorn.sxmobileoa.app.network

import org.joda.time.DateTime
import java.util.*

data class Params(
        val busiCode: String,
        val parameters: HashMap<String, Any>,
        val uuid: String = "776ef4e7-0edd-467b-9b4c-c33ec3fb4d8a",
        val version: String = "1.0",
        // 请求业务
        val loginName: String = "",
        val appId: String = "1c7bd52c-1bfe-11e6-b6ba-3e1d05defe78",
        // 64位以内流水号每次访问是唯一的
        val thirdFlow: String = UUID.randomUUID().toString(),
        // 随机数
//        val randCode: String = Md5Main.getRandom(),
        // 时间
        val time: String = DateTime().toString("yyyy-MM-dd HH:mm:ss"),
        val seqM: String = ""
) {

    // 登陆后的返回值用来获取其它参数
//    val ticket = Global.LOGIN_PARAMETERS?.ticket

    companion object {
        // md5密钥
        const val md5key = "8d021e610b57b6f1"
    }

    // md5加密后的字符串
//    val secM = Md5Main.sign(uuid + busiCode + thirdFlow + appId + randCode + md5key)

}
