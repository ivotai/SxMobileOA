package com.unicorn.sxmobileoa.app.config

import android.os.Environment
import dagger.Module
import dagger.Provides
import java.io.File

@Module
class ConfigModule {

    companion object {
        fun baseDir(): String {
            val downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val baseDir = File(downloadDir, "SxMobileOA")
            if (!baseDir.exists()) baseDir.mkdir()
            return baseDir.absolutePath
        }
    }


    @Provides
    @BaseUrl
    fun baseUrl() =
            "http://154.0.21.194:8189/busiGate/"
//            "http://154.0.66.127:8080/busiGate/"

}


