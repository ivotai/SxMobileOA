package com.unicorn.sxmobileoa.n

import android.annotation.SuppressLint
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.ui.BaseFra
import com.unicorn.sxmobileoa.simple.main.network.loginout.LoginOut
import kotlinx.android.synthetic.main.my_fra.*

class MyFra:BaseFra(){

    override val layoutId = R.layout.my_fra

    override fun initViews() {
        titleBar.setTitle("我的",true)
    }

    @SuppressLint("CheckResult")
    override fun bindIntent() {
        btnLoginout.safeClicks().subscribe {
            RxBus.get().post(LoginOut())
        }
    }

}