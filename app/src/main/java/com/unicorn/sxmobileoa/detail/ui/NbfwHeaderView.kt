package com.unicorn.sxmobileoa.detail.ui

import android.content.Context
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.get
import com.unicorn.sxmobileoa.app.set
import com.unicorn.sxmobileoa.detail.Editable

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
        Global.detail.get(Key.jbbm_input).let { etJbbm.setText(it) }
        etNgr = findViewById(R.id.etNgr)
        Global.detail.get(Key.ngr_input).let { etNgr.setText(it) }
        etZsmc = findViewById(R.id.etZsmc)
        Global.detail.get(Key.zsmc_input).let { etZsmc.setText(it) }
        etCsmc = findViewById(R.id.etCsmc)
        Global.detail.get(Key.csmc_input).let { etCsmc.setText(it) }

        //
        val currentNodeId = Global.dbxx.param.nodeId
        Editable().firstCould(currentNodeId).subscribe { enable ->
            etJbbm.isEnabled = enable
            etNgr.isEnabled = enable
            etZsmc.isEnabled = enable
            etCsmc.isEnabled = enable
            if (enable) watch()
        }
    }

    private fun watch() {
        RxTextView.textChanges(etJbbm).subscribe {
            Global.detail.set(Key.jbbm_input, it.toString())
        }
        RxTextView.textChanges(etNgr).subscribe {
            Global.detail.set(Key.ngr_input, it.toString())
        }
        RxTextView.textChanges(etZsmc).subscribe {
            Global.detail.set(Key.zsmc_input, it.toString())
        }
        RxTextView.textChanges(etCsmc).subscribe {
            Global.detail.set(Key.csmc_input, it.toString())
        }
    }

}