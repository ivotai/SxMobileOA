package com.unicorn.sxmobileoa.app.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.unicorn.sxmobileoa.R

class TitleBar(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    init {
        initViews(context)
    }

    private lateinit var tvTitle: TextView
    private lateinit var tvOperation: TextView

    fun initViews(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.title_bar, this, true)
        tvTitle = findViewById(R.id.tvTitle)
        tvOperation = findViewById(R.id.tvOperation)
    }

    fun setTitle(title: String) {
        tvTitle.text = title
    }

    fun setOperation(operation: String): View {
        tvOperation.text = operation
        return tvOperation
    }

}