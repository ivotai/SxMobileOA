package com.unicorn.sxmobileoa.app.union

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

class RxBus {

    companion object {

        private val rxBus = RxBus()

        fun get() = rxBus

    }

    private val subject: Subject<Any> = PublishSubject.create<Any>().toSerialized()

    fun post(obj: Any) {
        subject.onNext(obj)
    }

    fun <T> toObservable(clazz: Class<T>): Observable<T> = subject.ofType(clazz)
    
}