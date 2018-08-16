package com.unicorn.sxmobileoa.app

import android.widget.TextView
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

fun TextView.clicks(): Observable<Any> = RxView.clicks(this).throttleFirst(1, TimeUnit.SECONDS)

fun TextView.trimText() = this.text.toString().trim()

