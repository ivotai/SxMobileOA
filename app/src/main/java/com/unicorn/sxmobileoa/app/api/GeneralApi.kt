package com.unicorn.sxmobileoa.app.api

import com.unicorn.sxmobileoa.app.chongqing.BaseResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GeneralApi {

    @GET("request.shtml")
    operator fun get(@Query("params") params: String): Observable<BaseResponse>

}