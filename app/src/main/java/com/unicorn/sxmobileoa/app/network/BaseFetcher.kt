package com.unicorn.sxmobileoa.app.network

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import io.reactivex.Observable
import java.util.*

abstract class BaseFetcher<Parameters> {

    // 业务编码
    protected abstract val busiCode: String

    // 参数
    val parameters = HashMap<String, Any>()


    open fun initParameters() {

    }

    // 将 Response 转换成真正需要的 Parameters
    protected abstract fun map(response: Response): Parameters

    private val generalApi = ComponentHolder.appComponent.getGeneralApi()

    fun execute(): Observable<Parameters> {
        initParameters()
        val params = Params(busiCode, parameters)
        val json = ComponentHolder.appComponent.getGson().toJson(params)
        return Observable.empty()
    }

}
