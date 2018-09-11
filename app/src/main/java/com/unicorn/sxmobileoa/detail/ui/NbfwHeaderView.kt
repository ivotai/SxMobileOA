package com.unicorn.sxmobileoa.detail.ui

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.*
import com.unicorn.sxmobileoa.app.utils.RxBus
import com.unicorn.sxmobileoa.dept.model.DeptResult
import com.unicorn.sxmobileoa.dept.ui.DeptAct
import com.unicorn.sxmobileoa.detail.Editable
import io.reactivex.functions.Consumer

class NbfwHeaderView(context: Context) : FrameLayout(context) {

    init {
        initViews(context)
    }

    lateinit var tvBt: TextView
    lateinit var etJbbm: EditText
    lateinit var etNgr: EditText
    lateinit var tvZsmc: TextView
    lateinit var tvCsmc: TextView


    fun initViews(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.header_view_nbfw, this, true)
        tvBt = findViewById(R.id.tvBt)
        tvBt.text = Global.spd.spdXx.bt
        etJbbm = findViewById(R.id.etJbbm)
        Global.spd.get(Key.jbbm_input).let { etJbbm.setText(it) }
        etNgr = findViewById(R.id.etNgr)
        Global.spd.get(Key.ngr_input).let { etNgr.setText(it) }
        tvZsmc = findViewById(R.id.tvZsmc)
        Global.spd.get(Key.zsmc_input).let { tvZsmc.setText(it) }
        tvCsmc = findViewById(R.id.tvCsmc)
        Global.spd.get(Key.csmc_input).let { tvCsmc.setText(it) }

        //
        val currentNodeId = Global.dbxx.param.nodeId
        Editable().couldEdit(currentNodeId).subscribe { enable ->


            tvZsmc.safeClicks().subscribe {
                context.startActivity(Intent(context, DeptAct::class.java))
            }
            RxBus.get().registerEvent(DeptResult::class.java, context as LifecycleOwner, Consumer {
                if (it.tag == "zsmc")
                    tvZsmc.text = it.deptList
                            .map { dept -> dept.text }
                            .joinToString(",")
            })
//            tvZsmc.isEnabled = enable
//            tvCsmc.isEnabled = enable
            if (enable) watch()
        }
    }

    private fun watch() {
        RxTextView.textChanges(tvZsmc).subscribe {
            Global.spd.set(Key.zsmc_input, it.toString())
        }
        RxTextView.textChanges(tvCsmc).subscribe {
            Global.spd.set(Key.csmc_input, it.toString())
        }
    }

}