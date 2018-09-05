package com.unicorn.sxmobileoa.login

import android.annotation.SuppressLint
import android.text.TextUtils
import com.jakewharton.rxbinding2.widget.RxTextView
import com.orhanobut.logger.Logger
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.base.BaseAct
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.trimText
import com.unicorn.sxmobileoa.login.model.ValidationResult
import com.unicorn.sxmobileoa.login.useCase.LoginUseCase
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import kotlinx.android.synthetic.main.act_login.*

class LoginAct : BaseAct() {

    override val layoutId = R.layout.act_login

    @SuppressLint("SetTextI18n")
    override fun initViews() {
        etAccount.setText("0000")
        etPwd.setText("withub4l")
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

        btnLogin.safeClicks().subscribe { login() }
    }

    private fun login() {
        LoginUseCase(etAccount.trimText(), etPwd.trimText()).start(this)
                .subscribe({
                    Logger.e(it.toString())
                }, {
                    Logger.e(it.toString())
                })
//        Single.just(Any())
//                .compose(disposeOnDestroy(this))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    startActivity(Intent(this@LoginAct, BgsxAct::class.java))
//                }, {
//                    Logger.e(it.toString())
//                })
    }

}
