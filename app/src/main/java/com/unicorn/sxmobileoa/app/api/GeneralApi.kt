package com.unicorn.sxmobileoa.app.api

import io.reactivex.Observable
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface GeneralApi {

    @Headers("Content-Type: text/xml")
    @POST("request.shtml")
    fun post(@Body requestBody: RequestBody): Observable<ResponseBody>

}