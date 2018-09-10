package com.unicorn.sxmobileoa.detail.ui

import android.content.Context
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.get

class NbfwHeaderView(context: Context) : FrameLayout(context) {

    init {
        initViews(context)
    }

    lateinit var tvBt: TextView
    lateinit var etJbbm: EditText
    lateinit var etNgr: EditText
    lateinit var etZsmc: EditText
    lateinit var etCsmc: EditText

    fun initViews(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.header_view_nbfw, this, true)
        tvBt = findViewById(R.id.tvBt)
        tvBt.text = Global.detail.spdXx.bt
        etJbbm = findViewById(R.id.etJbbm)
        Global.detail.get("jbbm_input").let { etJbbm.setText(it) }
        etNgr = findViewById(R.id.etNgr)
        Global.detail.get("ngr_input").let { etNgr.setText(it) }
        etZsmc = findViewById(R.id.etZsmc)
        Global.detail.get("zsmc_input").let { etZsmc.setText(it) }
        etCsmc = findViewById(R.id.etCsmc)
        Global.detail.get("csmc_input").let { etCsmc.setText(it) }

        //
    }

}