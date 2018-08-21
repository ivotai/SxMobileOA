package com.unicorn.sxmobileoa.login

import android.annotation.SuppressLint
import android.content.Intent
import android.text.TextUtils
import com.jakewharton.rxbinding2.widget.RxTextView
import com.orhanobut.logger.Logger
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.base.BaseAct
import com.unicorn.sxmobileoa.app.clicks
import com.unicorn.sxmobileoa.app.trimText
import com.unicorn.sxmobileoa.business.shouWen.list.ShouWenListAct
import com.unicorn.sxmobileoa.login.model.LoginParameters
import com.unicorn.sxmobileoa.login.model.ValidationResult
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import kotlinx.android.synthetic.main.act_login.*

class LoginAct : BaseAct() {

    override val layoutId = R.layout.act_login

    @SuppressLint("SetTextI18n")
    override fun initViews() {
        etAccount.setText("审判管理员")
        etPwd.setText("withub3305")
    }

    private lateinit var validationResult: ValidationResult

    override fun bindIntent() {
        Observable.combineLatest(
                RxTextView.textChanges(etAccount),
                RxTextView.textChanges(etPwd),
                BiFunction<CharSequence, CharSequence, ValidationResult> { account, pwd ->
                    if (TextUtils.isEmpty(account))
                        return@BiFunction ValidationResult(false, "用户名不能为空")
                    if (TextUtils.isEmpty(pwd))
                        return@BiFunction ValidationResult(false, "密码不能为空")
                    ValidationResult()
                }).subscribe {
            validationResult = it
            btnLogin.isEnabled = it.result
        }

        btnLogin.clicks().subscribe { login() }
    }

    private fun login(){
        LoginFetcher(etAccount.trimText(), etPwd.trimText()).execute()
                .subscribe(object : Observer<LoginParameters> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: LoginParameters) {
                        Global.LOGIN_PARAMETERS = t
                        startActivity(Intent(this@LoginAct, ShouWenListAct::class.java))
                    }

                    override fun onError(e: Throwable) {
                        Logger.e(e.toString())
                    }
                })
    }

}
