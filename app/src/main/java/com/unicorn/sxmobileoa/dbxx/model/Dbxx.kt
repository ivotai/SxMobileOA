package com.unicorn.sxmobileoa.dbxx.model

import java.io.Serializable

data class Dbxx(
        val bt: String,
        val cjrq: String,
        val ngrDept: String,
        val ngrName: String,
        val nodeName: String,
        val param: Param,
        val wh: String
) : Serializable

data class Param(
        val nodeId: String,
        val primaryId: String,
        val processInstancesId: String,
        val taskId: String,
        val taskKey: String
) : Serializable