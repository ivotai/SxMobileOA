package com.unicorn.sxmobileoa.app.base

import android.os.Bundle
import me.yokeyword.fragmentation.SupportActivity

abstract class BaseAct : SupportActivity() {

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