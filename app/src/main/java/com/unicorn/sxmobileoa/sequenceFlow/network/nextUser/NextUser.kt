package com.unicorn.sxmobileoa.sequenceFlow.network.nextUser

import com.google.gson.reflect.TypeToken
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.select.deptUser.model.User
import com.unicorn.sxmobileoa.spd.model.Spd
import com.unicorn.sxmobileoa.sequenceFlow.model.NextTaskSequenceFlow

class NextUser(spd: Spd, nextTaskSequenceFlow: NextTaskSequenceFlow) : BaseUseCase<List<User>>() {

    init {
        request = NextUserRequest(spd, nextTaskSequenceFlow)
    }

    override fun toResult(json: String): List<User> {
        val type = object : TypeToken<List<User>>() {}.type
        return ComponentHolder.appComponent.getGson().fromJson<List<User>>(json, type)
    }
}