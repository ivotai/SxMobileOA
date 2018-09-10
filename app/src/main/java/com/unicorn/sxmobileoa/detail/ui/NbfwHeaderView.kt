package com.unicorn.sxmobileoa.detail.ui

import android.content.Context
import android.view.LayoutInflater
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
    lateinit var tvJbbm: TextView
    lateinit var tvNgr: TextView
    lateinit var tvZsmc: TextView
    lateinit var tvCsmc: TextView
    lateinit var tvFwwh: TextView
    lateinit var tvFwsj: TextView

    fun initViews(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.header_view_nbfw, this, true)
        tvBt = findViewById(R.id.tvBt)
        tvBt.text = detail.spdXx.bt
        tvJbbm = findViewById(R.id.tvJbbm)
        tvJbbm.text = detail.get("jbbm_input")
        tvNgr = findViewById(R.id.tvNgr)
        tvNgr.text = detail.get("ngr_input")
        tvZsmc = findViewById(R.id.tvZsmc)
        tvZsmc.text = detail.get("zsmc_input")
        tvCsmc = findViewById(R.id.tvCsmc)
        tvCsmc.text = detail.get("csmc_input")
        tvFwwh = findViewById(R.id.tvFwwh)
        tvFwwh.text = detail.get("sdwh")
        tvFwsj = findViewById(R.id.tvFwsj)
        tvFwsj.text = detail.get("fwsj_input")
    }

}