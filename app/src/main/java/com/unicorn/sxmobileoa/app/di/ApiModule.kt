package com.unicorn.sxmobileoa.app.di

import com.unicorn.sxmobileoa.app.api.GeneralApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideGeneralApi(retrofit: Retrofit): GeneralApi = retrofit.create(GeneralApi::class.java)

}