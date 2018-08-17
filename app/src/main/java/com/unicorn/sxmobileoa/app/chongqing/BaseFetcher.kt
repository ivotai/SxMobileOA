package com.unicorn.sxmobileoa.app.chongqing

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

abstract class BaseFetcher {

    // 业务编码
    protected abstract val busiCode: String

    // 参数
    protected abstract val parameters: HashMap<String, Any>

    private val generalApi = ComponentHolder.appComponent.getGeneralApi()

    fun execute(): Observable<BaseResponse> {
        val params = Params(busiCode, parameters).toString()
        return generalApi.get(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        //                .filter(response -> response.getCode().equals(SUCCESS_CODE))
        //                .map(this::map)
    }

}
