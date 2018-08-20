package com.unicorn.sxmobileoa.app.network

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
        var parameters: Any
)

