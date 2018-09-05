package com.unicorn.sxmobileoa.app.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseAct : AppCompatActivity() {

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        initViews()
        bindIntent()
    }

    abstract val layoutId: Int

    abstract fun initViews()

    abstract fun bindIntent()

}