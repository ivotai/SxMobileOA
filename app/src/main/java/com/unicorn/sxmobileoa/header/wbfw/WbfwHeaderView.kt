package com.unicorn.sxmobileoa.header.wbfw

import android.annotation.SuppressLint
import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.*
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.simple.dept.model.DeptSelectResult
import com.unicorn.sxmobileoa.simple.dept.ui.DeptAct
import com.unicorn.sxmobileoa.spd.helper.SpdHelper
import com.unicorn.sxmobileoa.spd.model.Spd
import io.reactivex.functions.Consumer

@SuppressLint("ViewConstructor")
class WbfwHeaderView(context: Context, dbxx: Dbxx, spd: Spd) : FrameLayout(context) {

    init {
        initViews(context, dbxx, spd)
    }

    lateinit var tvTitle: TextView
    lateinit var tvBt: TextView
    lateinit var tvJbbm: TextView
    lateinit var tvNgr: TextView
    lateinit var tvZsmc: TextView
    lateinit var tvCsmc: TextView

    fun initViews(context: Context, dbxx: Dbxx, spd: Spd) {
        LayoutInflater.from(context).inflate(R.layout.header_view_nbfw, this, true)
        findViews()
        renderViews(spd)
        canEdit(dbxx, spd)
    }

    private fun findViews() {
        tvTitle = findViewById(R.id.tvTitle)
        tvBt = findViewById(R.id.tvBt)
        tvJbbm = findViewById(R.id.tvJbbm)
        tvNgr = findViewById(R.id.tvNgr)
        tvZsmc = findViewById(R.id.tvZsmc)
        tvCsmc = findViewById(R.id.tvCsmc)
    }

    @SuppressLint("SetTextI18n")
    private fun renderViews(spd: Spd) {
        tvTitle.text = "${Global.court!!.dmms}内部发文"
        tvBt.text = spd.spdXx.bt
        spd.get(Key.jbbm_input).let { tvJbbm.text = it }
        spd.get(Key.ngr_input).let { tvNgr.text = it }
        spd.get(Key.zsmc_input).let { tvZsmc.text = it }
        spd.get(Key.csmc_input).let { tvCsmc.text = it }
    }

    private fun canEdit(dbxx: Dbxx, spd: Spd) {
        val currentNodeId = dbxx.param.nodeId
        SpdHelper().canEdit(currentNodeId).subscribe { canEdit ->
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
            RxBus.get().registerEvent(DeptSelectResult::class.java, context as LifecycleOwner, Consumer { deptResult ->
                val textView = if (deptResult.tag == Key.zsmc_input) tvZsmc else tvCsmc
                deptResult.deptList.joinToString(",") { dept -> dept.text }.let {
                    textView.text = it
                    spd.set(deptResult.tag, it)
                }
            })
        }
    }

}