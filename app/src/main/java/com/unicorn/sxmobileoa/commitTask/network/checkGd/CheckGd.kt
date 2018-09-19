package com.unicorn.sxmobileoa.commitTask.network.checkGd

import com.unicorn.sxmobileoa.app.network.BaseUseCase

class CheckGd(processInstanceId:String,taskId:String):BaseUseCase<Any>(){

    init {
        request = CheckGdRequest(processInstanceId,taskId)
    }

    override fun toResult(json: String): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}