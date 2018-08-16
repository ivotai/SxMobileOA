package com.unicorn.sxmobileoa.app.di

import com.unicorn.sxmobileoa.app.di.config.ConfigModule
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [ConfigModule::class, RetrofitModule::class])
interface AppComponent {

//    fun getRetrofit(): Retrofit

}