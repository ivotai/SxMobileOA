package com.unicorn.sxmobileoa.app.api;

import com.unicorn.sxmobileoa.app.chongqing.Response;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeneralApi {

    @GET("request.shtml")
    Observable<Response> get(@Query("params") String params);

}