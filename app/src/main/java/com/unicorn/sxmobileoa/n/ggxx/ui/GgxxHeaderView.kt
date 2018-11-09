package com.unicorn.sxmobileoa.n.ggxx.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.n.ggxx.model.Ggxx
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.header_view_ggxx.view.*

@SuppressLint("ViewConstructor")
class GgxxHeaderView(context: Context, ggxx: Ggxx) : FrameLayout(context), LayoutContainer {

    override val containerView = this

    init {
        initViews(context, ggxx)
    }

    fun initViews(context: Context, ggxx: Ggxx) {
        LayoutInflater.from(context).inflate(R.layout.header_view_ggxx, this, true)
        ggxx.apply {
            webView.loadData(content, "text/html", "UTF-8")
            tvFsr.text = fsrmc
            tvSysTime.text = systime
            tvJsr.text = jsrmc
        }
    }

}