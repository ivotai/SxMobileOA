package com.unicorn.sxmobileoa.app.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFra : Fragment() {

    abstract val layoutId: Int

    abstract fun initViews()

    abstract fun bindIntent()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = inflater.inflate(layoutId, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        bindIntent()
    }

}