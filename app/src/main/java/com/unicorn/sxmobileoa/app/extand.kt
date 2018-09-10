package com.unicorn.sxmobileoa.app

import android.app.Activity
import android.arch.lifecycle.LifecycleOwner
import android.content.Intent
import android.view.View
import android.widget.TextView
import com.jakewharton.rxbinding2.view.clicks
import com.unicorn.sxmobileoa.app.utils.MainThreadTransformer
import com.unicorn.sxmobileoa.detail.model.Detail
import florent37.github.com.rxlifecycle.RxLifecycle
import io.reactivex.Maybe
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

fun View.safeClicks(): Observable<Unit> = this.clicks().throttleFirst(1, TimeUnit.SECONDS)

fun TextView.trimText() = this.text.toString().trim()

fun <T> Maybe<T>.common(lifecycleOwner: LifecycleOwner): Maybe<T> = this.compose(MainThreadTransformer())
        .compose(RxLifecycle.with(lifecycleOwner).disposeOnDestroy())

fun Activity.startActivityAndFinish(intent: Intent) {
    this.startActivity(intent)
    finish()
}

fun Detail.get(spdKey: String):String {
    return this.spdData.filter { it.spdKey == spdKey }[0].spdValue
}
