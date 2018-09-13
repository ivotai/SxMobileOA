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
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.helper.SpdHelper
import com.unicorn.sxmobileoa.spd.model.Spd
import io.reactivex.functions.Consumer

@SuppressLint("ViewConstructor")
class WbfwHeaderView(context: Context, menu: Menu, dbxx: Dbxx, spd: Spd) : FrameLayout(context) {

    init {
        initViews(context, menu, dbxx, spd)
    }

    lateinit var tvTitle: TextView
    lateinit var tvBt: TextView
    lateinit var tvNgr: TextView
    lateinit var tvNgdw: TextView
    lateinit var tvMj: TextView
    lateinit var tvJdr: TextView
    lateinit var tvYssl: TextView
    lateinit var tvHj: TextView
    lateinit var tvYsdw: TextView
    private lateinit var pairList: ArrayList<Pair<TextView, String>>

    fun initViews(context: Context, menu: Menu, dbxx: Dbxx, spd: Spd) {
        LayoutInflater.from(context).inflate(R.layout.header_view_wbfw, this, true)
        findViews()
        renderViews(menu, spd)
        canEdit(dbxx, spd)
    }

    private fun findViews() {
        tvTitle = findViewById(R.id.tvTitle)
        tvBt = findViewById(R.id.tvBt)
        tvNgr = findViewById(R.id.tvNgr)
        tvNgdw = findViewById(R.id.tvNgdw)
        tvMj = findViewById(R.id.tvMj)
        tvJdr = findViewById(R.id.tvJdr)
        tvYssl = findViewById(R.id.tvYssl)
        tvHj = findViewById(R.id.tvHj)
        tvYsdw = findViewById(R.id.tvYsdw)
        pairList = ArrayList<Pair<TextView, String>>().apply {
            add(Pair(tvNgr, Key.ngr_input))
            add(Pair(tvNgdw, Key.ngdw_input))
            add(Pair(tvMj, Key.mjcd_input))
            add(Pair(tvJdr, Key.jdr_input))
            add(Pair(tvYssl, Key.yssl_input))
            add(Pair(tvHj, Key.hjcd_input))
            add(Pair(tvYsdw, Key.ysdw_input))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun renderViews(menu: Menu, spd: Spd) {
        tvTitle.text = "${Global.court!!.dmms}${menu.text}"
        spd.spdXx.bt.let { tvBt.text = it }
        pairList.forEach { pair -> spd.get(pair.second).let { pair.first.text = it } }
    }

    private fun canEdit(dbxx: Dbxx, spd: Spd) {
        val currentNodeId = dbxx.param.nodeId
        SpdHelper().canEdit(currentNodeId).subscribe { canEdit ->
            if (!canEdit) return@subscribe
            tvBt.apply {
                isEnabled = true
                textChanges().subscribe { spd.spdXx.bt = it }
            }
            pairList.forEach { pair ->
                pair.first.apply {
                    isEnabled = true
                    textChanges().subscribe { spd.set(pair.second, it) }
                }
            }
            // TODO 密级 缓急
            tvMj.safeClicks().subscribe {
                context.startActivity(Intent(context, DeptAct::class.java).apply {
                    putExtra(Key.tag, Key.zsmc_input)
                })
            }
            RxBus.get().registerEvent(DeptSelectResult::class.java, context as LifecycleOwner, Consumer { deptResult ->
                val textView = if (deptResult.tag == Key.zsmc_input) tvMj else tvJdr
                deptResult.deptList.joinToString(",") { dept -> dept.text }.let {
                    textView.text = it
                    spd.set(deptResult.tag, it)
                }
            })
        }
    }

}