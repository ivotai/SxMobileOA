package com.unicorn.sxmobileoa.detail.ui

import android.content.Context
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.get
import com.unicorn.sxmobileoa.detail.model.Detail

class NbfwHeaderView(context: Context, private val detail: Detail) : FrameLayout(context) {

    init {
        initViews(context)
    }

    lateinit var tvBt: TextView
    lateinit var etJbbm: EditText
    lateinit var etNgr: EditText
    lateinit var etZsmc: EditText
    lateinit var tvCsmc: TextView

    fun initViews(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.header_view_nbfw, this, true)
        tvBt = findViewById(R.id.tvBt)
        tvBt.text = detail.spdXx.bt
        etJbbm = findViewById(R.id.etJbbm)
        detail.get("jbbm_input").let { etJbbm.setText(it) }
        etNgr = findViewById(R.id.etNgr)
        detail.get("ngr_input").let { etNgr.setText(it) }
        etZsmc = findViewById(R.id.etZsmc)
        detail.get("zsmc_input").let { etZsmc.setText(it) }
        tvCsmc = findViewById(R.id.tvCsmc)
        tvCsmc.text = detail.get("csmc_input")
    }

}