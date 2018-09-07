package com.unicorn.sxmobileoa.app.config

import dagger.Module
import dagger.Provides

@Module
class ConfigModule {

    @Provides
    @BaseUrl
    fun baseUrl() =
            "http://154.0.21.193:8080/busiGate/"
//            "http://154.0.66.127:8080/busiGate/"

}


