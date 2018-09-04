package com.unicorn.sxmobileoa.app.api

import io.reactivex.Observable
import retrofit2.http.POST

interface GeneralApi {

    @POST("request.shtml")
    fun post(params: String): Observable<String>

}