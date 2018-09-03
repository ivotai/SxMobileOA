package com.unicorn.sxmobileoa.business.gwgl.fetcher

import com.unicorn.sxmobileoa.app.network.BaseFetcher

abstract class GeneralFetcher<T>(val type: String, private val page:Int) : BaseFetcher<T>() {

    override fun initParameters() {

        parameters.apply {
            // 1已办 2待办
            put("type",type)

        }
        // 分页参数
        parameters["offset"] = page
        parameters["limit"] = 5
    }

}