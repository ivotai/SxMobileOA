package com.unicorn.sxmobileoa.app.di.config

import dagger.Module
import dagger.Provides

@Module
class ConfigModule {

    @Provides
    @BaseUrl
    fun baseUrl() = "http://149.0.40.64:8080/ydbg-bs-1.0-SNAPSHOT/"

}


