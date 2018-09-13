package com.unicorn.sxmobileoa.header.wbfw

import android.annotation.SuppressLint
import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.*
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.header.BasicHeaderView
import com.unicorn.sxmobileoa.header.PAIR
import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.simple.dept.model.DeptResult
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.helper.SpdHelper
import com.unicorn.sxmobileoa.spd.model.Spd
import io.reactivex.functions.Consumer

@SuppressLint("ViewConstructor")
class WbfwHeaderView(context: Context, menu: Menu, dbxx: Dbxx, spd: Spd) : FrameLayout(context),
        BasicHeaderView {

    init {
        initViews(context, menu, dbxx, spd)
    }

    lateinit var tvTitle: TextView  // 标题外部发文
    lateinit var tvBt: TextView     // 真正标题
    lateinit var tvNgr: TextView
    lateinit var tvNgdw: TextView
    lateinit var tvMj: TextView     // CODE
    lateinit var tvJdr: TextView
    lateinit var tvYssl: TextView
    lateinit var tvHj: TextView     // CODE
    lateinit var tvYsdw: TextView
    lateinit var tvYssj: TextView
    lateinit var tvFwzh: TextView
    lateinit var tvFwsj: TextView
    lateinit var tvZsjg: TextView

    private lateinit var pairs: ArrayList<PAIR<TextView, String>>

    fun initViews(context: Context, menu: Menu, dbxx: Dbxx, spd: Spd) {
        LayoutInflater.from(context).inflate(R.layout.header_view_wbfw, this, true)
        findView()
        renderView(menu, spd)
        canEdit(dbxx, spd)
    }

    private fun findView() {
        tvTitle = findViewById(R.id.tvTitle)
        tvBt = findViewById(R.id.tvBt)          //
        tvNgr = findViewById(R.id.tvNgr)
        tvNgdw = findViewById(R.id.tvNgdw)
        tvMj = findViewById(R.id.tvMj)
        tvJdr = findViewById(R.id.tvJdr)
        tvYssl = findViewById(R.id.tvYssl)
        tvHj = findViewById(R.id.tvHj)
        tvYsdw = findViewById(R.id.tvYsdw)
        tvYssj = findViewById(R.id.tvYssj)
        tvFwzh = findViewById(R.id.tvFwzh)
        tvFwsj = findViewById(R.id.tvFwsj)
        tvZsjg = findViewById(R.id.tvZsjg)
        pairs = ArrayList<PAIR<TextView, String>>().apply {
            add(PAIR(tvNgr, Key.ngr_input))
            add(PAIR(tvNgdw, Key.ngdw_input))
            add(PAIR(tvMj, Key.mjcd_input))
            add(PAIR(tvJdr, Key.jdr_input))
            add(PAIR(tvYssl, Key.yssl_input))
            add(PAIR(tvHj, Key.hjcd_input))
            add(PAIR(tvYsdw, Key.ysdw_input))
            add(PAIR(tvYssj, Key.yssj_input))
            add(PAIR(tvFwsj, Key.fwsj_input))
            add(PAIR(tvZsjg, Key.zsjgmc_input))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun renderView(menu: Menu, spd: Spd) {
        tvTitle.text = "${Global.court!!.dmms}${menu.text}"
        spd.spdXx.bt.let { tvBt.text = it }
        spd.spdXx.sdwh.let { tvFwzh.text = it }
        // 遍历展示
        pairs.forEach {
            it.apply {
                textView.text = spd.get(key)
            }
        }
    }

    private fun canEdit(dbxx: Dbxx, spd: Spd) {
        if (!SpdHelper().canEdit2(dbxx.param.nodeId)) return

        // 标题的编辑无法统一处理
        tvBt.apply {
            isEnabled = true
            textChanges().subscribe { spd.spdXx.bt = it }
        }
        // 遍历，使其可编辑
        pairs.forEach {
            it.apply {
                textView.isEnabled = true
            }
        }

        // TODO 密级 缓急 CODE
        // TODO 印刷时间 发文时间 TIME
        // TODO 主送机关... DEPT
        // TODO DEPT USER
        tvZsjg.clickDept(Key.zsjgmc_input)
        RxBus.get().registerEvent(DeptResult::class.java, context as LifecycleOwner, Consumer { deptResult ->
            val target: TextView = when (deptResult.key) {
                Key.zsjgmc_input -> tvZsjg
                else -> tvZsjg
            }
            target.text = deptResult.result
        })
    }

    override fun saveToSpd(spd: Spd) {
        pairs.forEach {
            it.apply {
                spd.set(key, textView.trimText())
            }
        }
    }

}