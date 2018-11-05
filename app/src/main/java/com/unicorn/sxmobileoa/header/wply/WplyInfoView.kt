package com.unicorn.sxmobileoa.header.wply

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.*
import com.unicorn.sxmobileoa.app.mess.SpdHelper
import com.unicorn.sxmobileoa.header.BasicInfoView
import com.unicorn.sxmobileoa.header.PAIR
import com.unicorn.sxmobileoa.header.wply.detail.WplyDetailAct
import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.model.Spd

/*
      1. 永远不可编辑（包括标题）
      2. 基本信息
      3. 特殊字段
   */
@SuppressLint("ViewConstructor")
class WplyInfoView(context: Context, menu: Menu, spd: Spd) : FrameLayout(context),
        BasicInfoView {

    init {
        initViews(context, menu, spd)
    }

    lateinit var tvTitle: TextView
    lateinit var tvBt: TextView
    lateinit var tvSqsj: TextView
    lateinit var tvSqr: TextView
    lateinit var tvSqrdh: TextView
    lateinit var tvSqbm: TextView

    private lateinit var pairs: ArrayList<PAIR<TextView, String>>

    fun initViews(context: Context, menu: Menu, spd: Spd) {
        LayoutInflater.from(context).inflate(R.layout.header_view_wply, this, true)
        findView()
        renderView(menu, spd)
//        canEdit(dbxx)
    }

    private fun findView() {
        tvTitle = findViewById(R.id.tvTitle)
        tvBt = findViewById(R.id.tvBt)
        tvSqsj = findViewById(R.id.tvSqsj)
        tvSqr = findViewById(R.id.tvSqr)
        tvSqrdh = findViewById(R.id.tvSqrdh)
        tvSqbm = findViewById(R.id.tvSqbm)

        // 把 textView 和对应 key 放入 pair
        pairs = ArrayList<PAIR<TextView, String>>().apply {
            add(PAIR(tvSqsj, Key.sqsj_input))
            add(PAIR(tvSqr, Key.sqr_input))
            add(PAIR(tvSqrdh, Key.sqrdh_input))
            add(PAIR(tvSqbm, Key.sqbm_input))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun renderView(menu: Menu, spd: Spd) {
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
        val nodeId = dbxx.param.nodeId

        // 基本信息
        if (SpdHelper().canEdit2(nodeId)) {
            pairs.forEach {
                it.apply {
                    textView.isEnabled = true
                }
            }
        }

        findViewById<View>(R.id.tvWplyDetail).safeClicks().subscribe {
            context.startActivity(Intent(context, WplyDetailAct::class.java))
        }
    }

    override fun saveToSpd(spd: Spd):Boolean {
        pairs.forEach {
            it.apply {
                spd.set(key, textView.trimText())
            }
        }
        return true
    }

}