package com.unicorn.sxmobileoa.detail.ui

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.detail.model.Detail

class NbfwHeaderView(context: Context, private val detail: Detail) : FrameLayout(context) {

    init {
        initViews(context)
    }

    fun initViews(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.header_view_nbfw, this, true)
    }

}