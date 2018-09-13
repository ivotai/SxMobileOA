package com.unicorn.sxmobileoa.header.nbfw

import android.annotation.SuppressLint
import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.*
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.simple.dept.model.DeptResult
import com.unicorn.sxmobileoa.spd.helper.SpdHelper
import com.unicorn.sxmobileoa.spd.model.Spd
import io.reactivex.functions.Consumer

@SuppressLint("ViewConstructor")
class NbfwHeaderView(context: Context, dbxx: Dbxx, spd: Spd) : FrameLayout(context) {

    init {
        initViews(context, dbxx, spd)
    }

    lateinit var tvTitle: TextView
    lateinit var tvBt: TextView
    lateinit var tvJbbm: TextView
    lateinit var tvNgr: TextView
    lateinit var tvZsmc: TextView
    lateinit var tvCsmc: TextView
    private lateinit var pairList: ArrayList<Pair<TextView, String>>

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
        pairList = ArrayList<Pair<TextView, String>>().apply {
            add(Pair(tvJbbm, Key.jbbm_input))
            add(Pair(tvNgr, Key.ngr_input))
            add(Pair(tvZsmc, Key.zsmc_input))
            add(Pair(tvCsmc, Key.csmc_input))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun renderViews(spd: Spd) {
        tvTitle.text = "${Global.court!!.dmms}内部发文"
        tvBt.text = spd.spdXx.bt
        pairList.forEach { pair -> spd.get(pair.second).let { pair.first.text = it } }
    }

    private fun canEdit(dbxx: Dbxx, spd: Spd) {
        val currentNodeId = dbxx.param.nodeId
        SpdHelper().canEdit(currentNodeId).subscribe { canEdit ->
            if (!canEdit) return@subscribe
            pairList.forEach { pair ->
                pair.first.apply {
                    isEnabled = true
                    textChanges().subscribe { spd.set(pair.second, it) }
                }
            }
            tvZsmc.startDeptAct(Key.zsmc_input)
            tvCsmc.startDeptAct(Key.csmc_input)
            RxBus.get().registerEvent(DeptResult::class.java, context as LifecycleOwner, Consumer { deptResult ->
                val target: TextView = when (deptResult.key) {
                    Key.zsmc_input -> tvZsmc
                    else -> tvCsmc
                }
                target.text = deptResult.result
            })
        }
    }

}