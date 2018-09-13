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
    lateinit var etBt: TextView
    lateinit var etNgr: TextView
    lateinit var etNgdw: TextView
    lateinit var tvMj: TextView
    lateinit var tvJdr: TextView
    lateinit var tvYssl: TextView

    fun initViews(context: Context, menu: Menu, dbxx: Dbxx, spd: Spd) {
        LayoutInflater.from(context).inflate(R.layout.header_view_wbfw, this, true)
        findViews()
        renderViews(menu, spd)
        canEdit(dbxx, spd)
    }

    private fun findViews() {
        tvTitle = findViewById(R.id.tvTitle)
        etBt = findViewById(R.id.etBt)
        etNgr = findViewById(R.id.etNgr)
        etNgdw = findViewById(R.id.etNgdw)
        tvMj = findViewById(R.id.tvMj)
        tvJdr = findViewById(R.id.tvJdr)
        tvYssl = findViewById(R.id.tvYssl)
    }

    @SuppressLint("SetTextI18n")
    private fun renderViews(menu: Menu, spd: Spd) {
        tvTitle.text = "${Global.court!!.dmms}${menu.text}"
        spd.spdXx.bt.let { etBt.text = it }
        spd.get(Key.ngr_input).let { etNgr.text = it }
        spd.get(Key.ngdw_input).let { etNgdw.text = it }
        spd.get(Key.mjcd_input).let { tvMj.text = it }
        spd.get(Key.jdr_input).let { tvJdr.text = it }
        spd.get(Key.yssl_input).let { tvYssl.text = it }
    }

    private fun canEdit(dbxx: Dbxx, spd: Spd) {
        val currentNodeId = dbxx.param.nodeId
        SpdHelper().canEdit(currentNodeId).subscribe { canEdit ->
            if (!canEdit) return@subscribe
            etBt.apply {
                isEnabled = true
                textChanges().subscribe { spd.spdXx.bt = it }
            }
            etNgr.apply {
                isEnabled = true
                textChanges().subscribe { spd.set(Key.ngr_input, it) }
            }
            etNgdw.apply {
                isEnabled = true
                textChanges().subscribe { spd.set(Key.ngdw_input, it) }
            }
            // TODO MJ SELECT NOT DEPT
            tvMj.safeClicks().subscribe {
                context.startActivity(Intent(context, DeptAct::class.java).apply {
                    putExtra(Key.tag, Key.zsmc_input)
                })
            }
            tvJdr.apply {
                isEnabled = true
                textChanges().subscribe { spd.set(Key.jdr_input, it) }
            }
            tvYssl.apply {
                isEnabled = true
                textChanges().subscribe { spd.set(Key.yssl_input, it) }
            }
//            tvJdr.safeClicks().subscribe {
//                context.startActivity(Intent(context, DeptAct::class.java).apply {
//                    putExtra(Key.tag, Key.csmc_input)
//                })
//            }
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