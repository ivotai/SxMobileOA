package com.unicorn.sxmobileoa.app.config

import android.os.Environment
import dagger.Module
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

        const val baseUrl = "http://192.168.20.100:8090/reqChaOne/"

        const val baseAttachmentUrl = "${baseUrl}attachRedirect.do"

        const val defaultFydm = "R80"

    }

}


