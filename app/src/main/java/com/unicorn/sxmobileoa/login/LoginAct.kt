package com.unicorn.sxmobileoa.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.orhanobut.logger.Logger
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.chongqing.LoginFetcher
import com.unicorn.sxmobileoa.app.chongqing.Response
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import kotlinx.android.synthetic.main.act_login.*
import java.util.concurrent.TimeUnit

class LoginAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_login)
        s()
    }

    private lateinit var validationResult: ValidationResult

    private fun s() {
        Observable.combineLatest(
                RxTextView.textChanges(etAccount),
                RxTextView.textChanges(etPwd),
                BiFunction<CharSequence, CharSequence, ValidationResult> { account, pwd ->
                    if (TextUtils.isEmpty(account))
                        return@BiFunction ValidationResult(false, "用户名不能为空")
                    if (TextUtils.isEmpty(pwd))
                        return@BiFunction ValidationResult(false, "密码不能为空")
                    ValidationResult(true, "")
                }).subscribe {
            validationResult = it
            btnLogin.isEnabled = it.result
        }

        RxView.clicks(btnLogin)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe { login() }
    }

    private fun login() {
        LoginFetcher().start().subscribe(object : Observer<Response> {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: Response) {
                Logger.e(t.toString())
            }

            override fun onError(e: Throwable) {
                Logger.e(e.toString())
            }
        })
    }

}
