package com.unicorn.sxmobileoa.login.court.ui

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.blankj.utilcode.util.ConvertUtils
import com.unicorn.sxmobileoa.R

class CourtHeaderView(context: Context) : FrameLayout(context) {

    init {
        initViews(context)
    }

    lateinit var etKeyword: TextView

    fun initViews(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.header_view_court, this, true)
        etKeyword = findViewById(R.id.etKeyword)

        GradientDrawable().apply {
            setColor(ContextCompat.getColor(context, R.color.md_grey_200))
            cornerRadius = ConvertUtils.dp2px(5f).toFloat()
        }.let { etKeyword.background = it }
    }

}