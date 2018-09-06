package com.unicorn.sxmobileoa.app.di

import com.unicorn.sxmobileoa.app.api.UniqueApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideGeneralApi(retrofit: Retrofit): UniqueApi = retrofit.create(UniqueApi::class.java)

}