package com.unicorn.sxmobileoa.app.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.blankj.utilcode.util.ActivityUtils
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.safeClicks
import dart.Dart

abstract class BaseAct : AppCompatActivity(), ActOrFra {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Dart.bind(this)
        initArguments()
        setContentView(layoutId)
        initViews()
        bindIntent()
        registerEvent()

        val view = findViewById<View>(R.id.ivBack )
        view?.safeClicks()?.subscribe { ActivityUtils.getTopActivity().finish() }
    }

    override fun initArguments() {
    }

    override fun registerEvent() {
    }

}