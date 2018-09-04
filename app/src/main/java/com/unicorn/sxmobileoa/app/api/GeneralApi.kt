package com.unicorn.sxmobileoa.app.api

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface GeneralApi {

    @Headers("Content-Type: application/xml; charset=urf-8")
    @POST("request.shtml")
    fun post(@Body xml: String): Observable<String>

}