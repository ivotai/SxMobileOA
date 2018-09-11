package com.unicorn.sxmobileoa.app

import android.app.Activity
import android.arch.lifecycle.LifecycleOwner
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.jakewharton.rxbinding2.view.clicks
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.utils.MainThreadTransformer
import com.unicorn.sxmobileoa.detail.model.Spd
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
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

fun Spd.get(spdKey: String): String {
    val list = this.spdData.filter { it.spdKey == spdKey }
    return if (list.isEmpty()) "" else list[0].spdValue
}

fun Spd.set(spdKey: String, spdValue: String) {
    val list = this.spdData.filter { it.spdKey == spdKey }
    if (!list.isEmpty()) {
        list[0].spdValue = spdValue
    }
}

fun RecyclerView.addDefaultItemDecotation() {
    HorizontalDividerItemDecoration.Builder(context)
            .colorResId(R.color.md_grey_300)
            .size(1)
            .build().let { this.addItemDecoration(it) }
}
