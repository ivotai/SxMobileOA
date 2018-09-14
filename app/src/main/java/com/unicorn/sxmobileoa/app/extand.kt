package com.unicorn.sxmobileoa.app

import android.app.Activity
import android.arch.lifecycle.LifecycleOwner
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.widget.RxTextView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.mess.MainThreadTransformer
import com.unicorn.sxmobileoa.simple.code.ui.CodeAct
import com.unicorn.sxmobileoa.simple.dept.ui.DeptAct
import com.unicorn.sxmobileoa.spd.model.Spd
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import florent37.github.com.rxlifecycle.RxLifecycle
import io.reactivex.Maybe
import io.reactivex.Observable
import org.joda.time.DateTime
import java.util.concurrent.TimeUnit

fun View.safeClicks(): Observable<Unit> = this.clicks().throttleFirst(1, TimeUnit.SECONDS)

fun TextView.trimText() = this.text.toString().trim()

fun TextView.textChanges(): Observable<String> = RxTextView.textChanges(this).map { it.toString() }

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
        list[0].apply {
            if (this.spdValue == "") create = true else update = true
            this.spdValue = spdValue
            this.updateTime = DateTime().toString("yyyy-MM-dd HH:mm:ss")
        }
    }
}

fun RecyclerView.addDefaultItemDecoration() {
    HorizontalDividerItemDecoration.Builder(context)
            .colorResId(R.color.md_grey_300)
            .size(1)
            .build().let { this.addItemDecoration(it) }
}

fun TextView.clickDept(key: String) {
    this.safeClicks().subscribe {
        context.startActivity(Intent(context, DeptAct::class.java).apply {
            putExtra(Key.key, key)
        })
    }
}

fun TextView.clickCode(code: String, key: String, title: String) {
    this.safeClicks().subscribe {
        context.startActivity(Intent(context, CodeAct::class.java).apply {
            putExtra(Key.code, code)
            putExtra(Key.key, key)
            putExtra(Key.title, title)
        })
    }
}