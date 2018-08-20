package com.unicorn.sxmobileoa.app.chongqing

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

abstract class BaseFetcher<Parameters> {

    // 业务编码
    protected abstract val busiCode: String

    // 参数
    protected abstract val parameters: HashMap<String, Any>

    // 将 Response 转换成真正需要的 Parameters
    protected abstract fun map(response: Response): Parameters

    private val generalApi = ComponentHolder.appComponent.getGeneralApi()

    fun execute(): Observable<Parameters> {
        val params = Params(busiCode, parameters)
        val json = ComponentHolder.appComponent.getGson().toJson(params)
        return generalApi.get(json)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter { it.code == "000000" }
                .map(this::map)
    }

}
