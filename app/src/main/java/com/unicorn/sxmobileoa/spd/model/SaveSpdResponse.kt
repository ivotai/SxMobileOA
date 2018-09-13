package com.unicorn.sxmobileoa.spd.model

data class SaveSpdResponse(
    val taskId: String,
    val nodeId: String,
    val fydm: String,
    val processInstancesId: String,
    val currentTaskName: String,
    val status: String,
    val spdCode: String,
    val primaryId: String,
    val flowCode: String,
    val spyjId: String,
    val moduleCode: String,
    val currentTaskId: String
)