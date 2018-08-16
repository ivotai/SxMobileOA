package com.unicorn.sxmobileoa.app.di.config

import dagger.Module
import dagger.Provides

@Module
class ConfigModule {

    @Provides
    @BaseUrl
    fun baseUrl() = "http://127.0.0.1:8080/"

}


