package com.unicorn.sxmobileoa.app.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseAct : AppCompatActivity(), ActOrFra {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        initViews()
        bindIntent()
    }

}