package com.unicorn.sxmobileoa.spdNext.network.nextUser

import com.google.gson.reflect.TypeToken
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.spd.model.Spd
import com.unicorn.sxmobileoa.spdNext.model.NextTaskSequenceFlow
import com.unicorn.sxmobileoa.spdNext.model.User2

class NextUser(spd: Spd, nextTaskSequenceFlow: NextTaskSequenceFlow) : BaseUseCase<List<User2>>() {

    init {
        request = NextUserRequest(spd, nextTaskSequenceFlow)
    }

    override fun toResult(json: String): List<User2> {
        val type = object : TypeToken<List<User2>>() {}.type
        return ComponentHolder.appComponent.getGson().fromJson<List<User2>>(json, type)
    }
}