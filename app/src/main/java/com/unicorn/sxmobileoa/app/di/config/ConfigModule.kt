package com.unicorn.sxmobileoa.app.di.config

import dagger.Module
import dagger.Provides

@Module
class ConfigModule {

    @Provides
    @BaseUrl
    fun baseUrl() = "https://www.apiopen.top/"

}


