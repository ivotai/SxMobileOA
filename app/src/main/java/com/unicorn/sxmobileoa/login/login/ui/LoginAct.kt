package com.unicorn.sxmobileoa.login.login.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import com.blankj.utilcode.util.ConvertUtils
import com.github.florent37.rxsharedpreferences.RxSharedPreferences
import com.jakewharton.rxbinding2.widget.RxTextView
import com.orhanobut.logger.Logger
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.*
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.app.utils.RxBus
import com.unicorn.sxmobileoa.login.court.model.Court
import com.unicorn.sxmobileoa.login.court.ui.CourtAct
import com.unicorn.sxmobileoa.login.login.network.LoginUseCase
import com.unicorn.sxmobileoa.main.main.ui.MainAct
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function3
import kotlinx.android.synthetic.main.act_login.*

class LoginAct : BaseAct() {

    override val layoutId = R.layout.act_login

    @SuppressLint("SetTextI18n")
    override fun initViews() {
        // TODO DELETE
        etPassword.setText("withub4l")

        GradientDrawable().apply {
            val colorPrimary = ContextCompat.getColor(this@LoginAct, R.color.colorPrimary)
            setColor(colorPrimary)
            cornerRadius = ConvertUtils.dp2px(5f).toFloat()
        }.let { btnLogin.background = it }
    }

    override fun bindIntent() {
        Observable.combineLatest(
                RxTextView.textChanges(tvCourt).map { !TextUtils.isEmpty(it) },
                RxTextView.textChanges(etUsername).map { !TextUtils.isEmpty(it) },
                RxTextView.textChanges(etPassword).map { !TextUtils.isEmpty(it) },
                Function3<Boolean, Boolean, Boolean, Boolean> { court, username, password -> return@Function3 court && username && password })
                .subscribe { btnLogin.isEnabled = it }
        tvCourt.safeClicks().subscribe { startActivity(Intent(this@LoginAct, CourtAct::class.java)) }
        btnLogin.safeClicks().subscribe { login() }

        restoreInputInfo()
    }

    private fun login() {
        LoginUseCase(etUsername.trimText(), etPassword.trimText())
                .toMaybe(this)
                .subscribe({
                    Global.loginInfo = it
                    saveInputInfo()
                    startActivityAndFinish(Intent(this@LoginAct, MainAct::class.java))
                }, {
                    Logger.e(it.toString())
                })

        // TODO DELETE FAKER METHOD
//        Faker().getLoginMaybe().subscribe {
//            startActivityAndFinish(Intent(this@LoginAct, MainAct::class.java))
//        }
    }

    private fun restoreInputInfo() {
        val rxSharedPreferences = RxSharedPreferences.with(this)
        rxSharedPreferences.getString(Key.courtStr, "")
                .map { ComponentHolder.appComponent.getGson().fromJson(it, Court::class.java) }
                .subscribe {
                    Global.court = it
                    tvCourt.text = it.dmms
                }
        rxSharedPreferences.getString(Key.username, "")
                .subscribe { etUsername.setText(it) }
     }

    private fun saveInputInfo() {
        RxSharedPreferences.with(this).apply {
            putString(Key.courtStr, ComponentHolder.appComponent.getGson().toJson(Global.court)).subscribe {  }
            putString(Key.username, etUsername.trimText()).subscribe {  }
        }
    }


    override fun registerEvent() {
        RxBus.get().registerEvent(Court::class.java, this, Consumer { court ->
            Global.court = court
            tvCourt.text = court.dmms
        })
    }

}
