package com.unicorn.sxmobileoa.app.utils

import android.arch.lifecycle.LifecycleOwner
import florent37.github.com.rxlifecycle.RxLifecycle
import io.reactivex.Observable
import io.reactivex.functions.Consumer
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

    private fun <T> toObservable(clazz: Class<T>): Observable<T> = subject.ofType(clazz)

    fun <T> registerEvent(objectClazz: Class<T>, lifecycleOwner: LifecycleOwner, consumer: Consumer<T>) {
        this.toObservable(objectClazz)
                .compose(RxLifecycle.disposeOnDestroy(lifecycleOwner))
                .subscribe(consumer)
    }

}