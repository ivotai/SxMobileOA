package com.unicorn.sxmobileoa.app.di.config

import dagger.Module
import dagger.Provides

@Module
class ConfigModule {

    @Provides
    @BaseUrl
    fun baseUrl() = "http://154.0.66.127:80/busiGate/"

}


