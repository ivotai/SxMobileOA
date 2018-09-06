package com.unicorn.sxmobileoa.app

import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.jakewharton.rxbinding2.view.clicks
import com.unicorn.sxmobileoa.app.utils.MainThreadTransformer
import florent37.github.com.rxlifecycle.RxLifecycle
import io.reactivex.Maybe
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

fun View.safeClicks(): Observable<Unit> = this.clicks().throttleFirst(1, TimeUnit.SECONDS)

fun TextView.trimText() = this.text.toString().trim()

fun <T> Maybe<T>.common(act: AppCompatActivity) = this.compose(MainThreadTransformer())
        .compose(RxLifecycle.with(act).disposeOnDestroy())