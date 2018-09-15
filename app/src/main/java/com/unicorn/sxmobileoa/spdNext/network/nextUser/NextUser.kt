package com.unicorn.sxmobileoa.spdNext.network.nextUser

import com.google.gson.reflect.TypeToken
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.spd.model.Spd
import com.unicorn.sxmobileoa.spdNext.model.NextTaskSequenceFlow
import com.unicorn.sxmobileoa.spdNext.model.FlowUser

class NextUser(spd: Spd, nextTaskSequenceFlow: NextTaskSequenceFlow) : BaseUseCase<List<FlowUser>>() {

    init {
        request = NextUserRequest(spd, nextTaskSequenceFlow)
    }

    override fun toResult(json: String): List<FlowUser> {
        val type = object : TypeToken<List<FlowUser>>() {}.type
        return ComponentHolder.appComponent.getGson().fromJson<List<FlowUser>>(json, type)
    }
}