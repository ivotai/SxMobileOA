package com.unicorn.sxmobileoa.spdNext.network.nextUser

import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.spd.model.Spd
import com.unicorn.sxmobileoa.spdNext.model.NextTaskSequenceFlow

class NextUser(spd:Spd, nextTaskSequenceFlow:NextTaskSequenceFlow):BaseUseCase<Any>(){

    init {
        request = NextUserRequest(spd,nextTaskSequenceFlow)
    }

    override fun toResult(json: String): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}