package com.unicorn.sxmobileoa.dblb.model

data class Dblb(
    val bt: String,
    val cjrq: String,
    val ngrDept: String,
    val ngrName: String,
    val nodeName: String,
    val param: Param,
    val wh: String
)

data class Param(
    val primaryId: String,
    val processInstancesId: String,
    val taskId: String,
    val taskKey: String
)