package com.unicorn.sxmobileoa.header.sbwx

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
class SbwxHeaderView(context: Context, menu: Menu, dbxx: Dbxx, spd: Spd) : FrameLayout(context),
        BasicHeaderView {

    init {
        initViews(context,menu, dbxx, spd)
    }

    lateinit var tvTitle: TextView  // 内部发文标题
    lateinit var tvBt: TextView     // 真的标题
    lateinit var tvJbbm: TextView   // 不可编辑
    lateinit var tvNgr: TextView    // 不可编辑
    lateinit var tvZsmc: TextView
    lateinit var tvCsmc: TextView

    private lateinit var pairs: ArrayList<PAIR<TextView, String>>

    fun initViews(context: Context,menu: Menu, dbxx: Dbxx, spd: Spd) {
        LayoutInflater.from(context).inflate(R.layout.header_view_sbwx, this, true)
        findView()
        renderView(menu,spd)
        canEdit(dbxx)
    }

    private fun findView() {
        tvTitle = findViewById(R.id.tvTitle)
        tvBt = findViewById(R.id.tvBt)
        tvJbbm = findViewById(R.id.tvJbbm)
        tvNgr = findViewById(R.id.tvNgr)
        tvZsmc = findViewById(R.id.tvZsmc)
        tvCsmc = findViewById(R.id.tvCsmc)

        // 把 textView 和对应 key 放入 pair
        pairs = ArrayList<PAIR<TextView, String>>().apply {
            add(PAIR(tvJbbm, Key.jbbm_input))
            add(PAIR(tvNgr, Key.ngr_input))
            add(PAIR(tvZsmc, Key.zsmc_input))
            add(PAIR(tvCsmc, Key.csmc_input))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun renderView(menu: Menu,spd: Spd) {
        tvTitle.text = "${Global.court!!.dmms}${menu.text}"
        tvBt.text = spd.spdXx.bt
        // 遍历展示
        pairs.forEach {
            it.apply {
                textView.text = spd.get(key)
            }
        }
    }

    private fun canEdit(dbxx: Dbxx) {
        if (!SpdHelper().canEdit2(dbxx.param.nodeId)) return

        // 遍历，使其可编辑
        pairs.forEach {
            it.apply {
                textView.isEnabled = true
            }
        }

        // 选择部门
        tvZsmc.clickDept(Key.zsmc_input)
        tvCsmc.clickDept(Key.csmc_input)
        RxBus.get().registerEvent(DeptResult::class.java, context as LifecycleOwner, Consumer { deptResult ->
            when (deptResult.key) {
                Key.zsmc_input -> tvZsmc
                else -> tvCsmc
            }.text = deptResult.result
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