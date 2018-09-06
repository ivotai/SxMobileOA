package com.unicorn.sxmobileoa.app.network

import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.Key

abstract class FydmUseCase<Model> : BaseUseCase<Model>() {

    override fun addParameters() {
        Global.court?.let { addParameter(Key.fydm, it.dm) }
    }

}