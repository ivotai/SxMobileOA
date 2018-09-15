package com.unicorn.sxmobileoa.commitTask.network

import com.unicorn.sxmobileoa.app.network.BaseUseCase
import com.unicorn.sxmobileoa.spdNext.model.TaskInstance

class CommitTask(taskInstantce: TaskInstance) : BaseUseCase<Any>() {

    init {
        request = CommitTaskRequest(taskInstantce)
    }

    override fun toResult(json: String): Any {
        return Any()
    }

}
