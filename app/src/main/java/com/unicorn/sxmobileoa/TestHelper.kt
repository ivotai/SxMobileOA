package com.unicorn.sxmobileoa

import com.unicorn.sxmobileoa.business.shouWen.ShouWen
import io.reactivex.Observable
import java.util.*

object TestHelper {

    fun getShouWenObservable(): Observable<List<ShouWen>> = Observable.create<List<ShouWen>> {
        val list = ArrayList<ShouWen>()
        list.add(ShouWen("测试用收文", "渝高法办20185", "审判管理员", "办公室", Date().time))
        list.add(ShouWen("测试用收文2", "渝高法办20185", "审判管理员", "办公室", Date().time))
        list.add(ShouWen("测试用收文3", "渝高法办20185", "审判管理员", "办公室", Date().time))
        list.add(ShouWen("测试用收文4", "渝高法办20185", "审判管理员", "办公室", Date().time))
        list.add(ShouWen("测试用收文5", "渝高法办20185", "审判管理员", "办公室", Date().time))
        it.onNext(list)
        it.onComplete()
    }

}