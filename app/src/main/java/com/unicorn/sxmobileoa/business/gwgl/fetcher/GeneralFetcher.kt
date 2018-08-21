package com.unicorn.sxmobileoa.business.gwgl.fetcher

import com.unicorn.sxmobileoa.app.network.BaseFetcher

abstract class GeneralFetcher<T>(val type: String,val page:Int) : BaseFetcher<T>() {

    override fun initParameters() {
        // 1已办 2待办
        parameters["type"] = type

        // 分页参数
        parameters["offset"] = page
        parameters["limit"] = 5
    }

}