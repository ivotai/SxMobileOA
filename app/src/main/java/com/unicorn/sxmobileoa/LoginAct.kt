package com.unicorn.sxmobileoa

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import kotlinx.android.synthetic.main.act_login.*

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
            com.orhanobut.logger.Logger.e(it.toString())
        }
    }

}
