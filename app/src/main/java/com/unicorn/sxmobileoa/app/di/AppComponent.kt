package com.unicorn.sxmobileoa.app.di

import com.unicorn.sxmobileoa.app.api.GeneralApi
import com.unicorn.sxmobileoa.app.di.config.ConfigModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ConfigModule::class, RetrofitModule::class,ApiModule::class])
interface AppComponent {

    fun getGeneralApi(): GeneralApi

//    fun inject(o:LoginAct)

}