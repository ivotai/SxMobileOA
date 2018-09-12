package com.unicorn.sxmobileoa.login.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.text.TextUtils
import com.github.florent37.rxsharedpreferences.RxSharedPreferences
import com.jakewharton.rxbinding2.widget.RxTextView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.*
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.login.network.LoginUseCase
import com.unicorn.sxmobileoa.simple.main.ui.MainAct
import com.unicorn.sxmobileoa.simple.court.model.Court
import com.unicorn.sxmobileoa.simple.court.ui.CourtAct
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function3
import kotlinx.android.synthetic.main.act_login.*

class LoginAct : BaseAct() {

    override val layoutId = R.layout.act_login

    // TODO DELETE
    @SuppressLint("SetTextI18n")
    override fun initViews() {
//        etPassword.setText("withub4l")
        etPassword.setText("zyadmin")
    }

    override fun bindIntent() {
        observeInput()
        tvCourt.safeClicks().subscribe { startActivity(Intent(this@LoginAct, CourtAct::class.java)) }
        btnLogin.safeClicks().subscribe {
            LoginUseCase(etUsername.trimText(), etPassword.trimText()).toMaybe(this).subscribe { loginInfo ->
                Global.loginInfo = loginInfo
                saveInputInfo()
                startActivityAndFinish(Intent(this@LoginAct, MainAct::class.java))
            }
        }
        restoreInputInfo()
    }

    private fun observeInput() {
        Observable.combineLatest(
                RxTextView.textChanges(tvCourt).map { !TextUtils.isEmpty(it) },
                RxTextView.textChanges(etUsername).map { !TextUtils.isEmpty(it) },
                RxTextView.textChanges(etPassword).map { !TextUtils.isEmpty(it) },
                Function3<Boolean, Boolean, Boolean, Boolean> { court, username, password -> return@Function3 court && username && password })
                .subscribe { btnLogin.isEnabled = it }
    }

    private fun restoreInputInfo() {
        RxSharedPreferences.with(this).apply {
            getString(Key.courtStr, "")
                    .map { ComponentHolder.appComponent.getGson().fromJson(it, Court::class.java) }
                    .subscribe { court ->
                        Global.court = court
                        tvCourt.text = court.dmms
                    }
            getString(Key.username, "").subscribe { etUsername.setText(it) }
        }
    }

    private fun saveInputInfo() {
        RxSharedPreferences.with(this).apply {
            putString(Key.courtStr, ComponentHolder.appComponent.getGson().toJson(Global.court)).subscribe { }
            putString(Key.username, etUsername.trimText()).subscribe { }
        }
    }

    override fun registerEvent() {
        RxBus.get().registerEvent(Court::class.java, this, Consumer { court ->
            Global.court = court
            tvCourt.text = court.dmms
        })
    }

}
