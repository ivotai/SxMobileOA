package com.unicorn.sxmobileoa.business.gwgl.fetcher

abstract class GwglFetcher<T>(page:String,type:Int):GeneralFetcher<T>(page,type){

    override fun initParameters() {
        super.initParameters()
        parameters["moduleCode"] = "OA_FUN_GWGL"
    }

}