package com.unicorn.sxmobileoa.app.chongqing

import com.google.gson.internal.LinkedTreeMap

data class Response(
        var code: String,
        var msg: String,
        var busiCode: String,
        var randCode: String,
        var time: String,
        var seqM: String,
        var secM: String,
        var seqD: String,
        var seqR: String,
        var thirdFlow: String,
        var parameters: LinkedTreeMap<String, String>? = null
) {

    /**
     * code : 000000
     * msg : 请求成功
     * busiCode : getFyList
     * randCode : 92cfb7f8-5c05-42d5-9247-36a67829d5a3
     * time : 20170622141844
     * seqM :
     * secM :
     * seqD :
     * seqR :
     * thirdFlow : 1234567
     */

}

