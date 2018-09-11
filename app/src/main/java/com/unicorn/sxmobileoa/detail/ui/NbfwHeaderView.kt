package com.unicorn.sxmobileoa.detail.ui

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.get
import com.unicorn.sxmobileoa.app.safeClicks
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
    lateinit var tvJbbm: TextView
    lateinit var tvNgr: TextView
    lateinit var tvZsmc: TextView
    lateinit var tvCsmc: TextView

    fun initViews(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.header_view_nbfw, this, true)
        findViews()
        renderViews()
        checkCanEdit()
    }

    private fun findViews() {
        tvBt = findViewById(R.id.tvBt)
        tvJbbm = findViewById(R.id.tvJbbm)
        tvNgr = findViewById(R.id.tvNgr)
        tvZsmc = findViewById(R.id.tvZsmc)
        tvCsmc = findViewById(R.id.tvCsmc)
    }

    private fun renderViews() {
        tvBt.text = Global.spd.spdXx.bt
        Global.spd.get(Key.jbbm_input).let { tvJbbm.text = it }
        Global.spd.get(Key.ngr_input).let { tvNgr.text = it }
        Global.spd.get(Key.zsmc_input).let { tvZsmc.text = it }
        Global.spd.get(Key.csmc_input).let { tvCsmc.text = it }
    }

    private fun checkCanEdit() {
        val currentNodeId = Global.dbxx.param.nodeId
        Editable().couldEdit(currentNodeId).subscribe { canEdit ->
            if (!canEdit) return@subscribe

            tvZsmc.safeClicks().subscribe {
                context.startActivity(Intent(context, DeptAct::class.java).apply {
                    putExtra(Key.tag, Key.zsmc_input)
                })
            }
            tvCsmc.safeClicks().subscribe {
                context.startActivity(Intent(context, DeptAct::class.java).apply {
                    putExtra(Key.tag, Key.csmc_input)
                })
            }
            RxBus.get().registerEvent(DeptResult::class.java, context as LifecycleOwner, Consumer { deptResult ->
                val textView = if (deptResult.tag == Key.zsmc_input) tvZsmc else tvCsmc
                deptResult.deptList.joinToString(",") { dept -> dept.text }.let { textView.text = it }
            })
        }
    }

}