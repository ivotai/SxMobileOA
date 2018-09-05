package com.unicorn.sxmobileoa.app.di

import com.google.gson.Gson
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.unicorn.sxmobileoa.BuildConfig
import com.unicorn.sxmobileoa.app.di.config.BaseUrl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun providerLoggingInterceptor(): LoggingInterceptor = LoggingInterceptor.Builder()
            .loggable(BuildConfig.DEBUG)
            .setLevel(Level.BASIC)
            .log(Platform.INFO)
            .request("Request")
            .response("Response")
            .addHeader("version", BuildConfig.VERSION_NAME)
            .addQueryParam("query", "0")
            //              .enableAndroidStudio_v3_LogsHack(true) /* enable fix for logCat logging issues with pretty format */
            //              .logger(new Logger() {
            //                  @Override
            //                  public void log(int level, String tag, String msg) {
            //                      Log.w(tag, msg);
            //                  }
            //              })
            //              .executor(Executors.newSingleThreadExecutor())
            .build()


    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: LoggingInterceptor): OkHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
            // TODO 可能需要配置更多参数
            .readTimeout(1,TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(@BaseUrl baseUrl: String, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
//            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Singleton
    @Provides
    fun providerGson(): Gson = Gson()

}
