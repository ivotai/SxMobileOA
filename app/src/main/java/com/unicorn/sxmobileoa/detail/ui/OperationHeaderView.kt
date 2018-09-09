package com.unicorn.sxmobileoa.detail.ui

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.unicorn.sxmobileoa.R

class OperationHeaderView(context: Context) : FrameLayout(context) {

    init {
        initViews(context)
    }

    fun initViews(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.header_view_operation, this, true)
    }

}