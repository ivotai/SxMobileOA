package com.unicorn.sxmobileoa.app

import android.app.Activity
import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.widget.RxTextView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.mess.MainThreadTransformer
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.select.code.ui.CodeAct
import com.unicorn.sxmobileoa.select.dept.ui.DeptAct
import com.unicorn.sxmobileoa.select.deptUser.ui.DeptUserAct
import com.unicorn.sxmobileoa.spd.model.Spd
import com.unicorn.sxmobileoa.spd.model.SpdData
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import florent37.github.com.rxlifecycle.RxLifecycle
import io.reactivex.Maybe
import io.reactivex.Observable
import org.joda.time.DateTime
import java.util.*
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
            this.spdValue = spdValue
            this.update = true
            this.updateTime = DateTime().toString("yyyy-MM-dd HH:mm:ss")
        }
    }else{
        val spdData = SpdData(spdKey = spdKey,spdValue = spdValue,spdid = this.spdXx.id)
        this.spdData.add(spdData)
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

fun TextView.clickDeptUser(type: String, key: String?) {
    this.safeClicks().subscribe {
        context.startActivity(Intent(context, DeptUserAct::class.java).apply {
            putExtra(Key.type, type)
            if (key != null) putExtra(Key.key, key)
        })
    }
}

fun TextView.clickCode(title: String, code: String, key: String) {
    this.safeClicks().subscribe {
        context.startActivity(Intent(context, CodeAct::class.java).apply {
            putExtra(Key.title, title)
            putExtra(Key.code, code)
            putExtra(Key.key, key)
        })
    }
}

fun Context.finish() {
    if (this is AppCompatActivity) {
        this.finish()
    }
}

fun TextView.clickDate(){
    this.safeClicks().subscribe {
        val now = Calendar.getInstance()
        val activity = context as BaseAct
        val dpd = DatePickerDialog.newInstance(
                { _, year, monthOfYear, dayOfMonth ->
                    val month = monthOfYear + 1
                    val monthStr = if (month > 9) "$month" else "0$month"
                    val dayStr = if (dayOfMonth > 9) "$dayOfMonth" else "0$dayOfMonth"
                    val str = "$year-$monthStr-$dayStr"
                    this.text = str
                },
                now.get(Calendar.YEAR), // Initial year selection
                now.get(Calendar.MONTH), // Initial month selection
                now.get(Calendar.DAY_OF_MONTH) // Inital day selection
        )
        dpd.show(activity.fragmentManager, "dpd")
    }
}