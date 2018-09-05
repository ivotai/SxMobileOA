package com.unicorn.sxmobileoa.app.api

import com.unicorn.sxmobileoa.network.model.Response
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface GeneralApi {

//    @Headers("Content-Type: text/xml")
    @POST("request.shtml")
    fun post(@Body requestBody: RequestBody): Single<Response>

}