package com.unicorn.sxmobileoa.header.sbly

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
import com.unicorn.sxmobileoa.header.BasicHeaderView
import com.unicorn.sxmobileoa.header.PAIR
import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.model.Spd

/*
      1. 永远不可编辑（包括标题）
      2. 基本信息
      3. 特殊字段
   */
@SuppressLint("ViewConstructor")
class SblyHeaderView(context: Context, menu: Menu, dbxx: Dbxx, spd: Spd) : FrameLayout(context),
        BasicHeaderView {

    init {
        initViews(context, menu, dbxx, spd)
    }

    lateinit var tvTitle: TextView
    lateinit var tvBt: TextView
    lateinit var tvSqbm: TextView
    lateinit var tvSqr: TextView
    lateinit var tvSqsj: TextView
    lateinit var tvLyxq: TextView
    lateinit var tvLyrq: TextView

    private lateinit var pairs: ArrayList<PAIR<TextView, String>>

    fun initViews(context: Context, menu: Menu, dbxx: Dbxx, spd: Spd) {
        LayoutInflater.from(context).inflate(R.layout.header_view_sbly, this, true)
        findView()
        renderView(menu, spd)
        canEdit(dbxx)
    }

    private fun findView() {
        tvTitle = findViewById(R.id.tvTitle)
        tvBt = findViewById(R.id.tvBt)
        tvSqbm = findViewById(R.id.tvSqbm)
        tvSqr = findViewById(R.id.tvSqr)
        tvSqsj = findViewById(R.id.tvSqsj)
        tvLyxq = findViewById(R.id.tvLyxq)
        tvLyrq = findViewById(R.id.tvLyrq)

        // 把 textView 和对应 key 放入 pair
        pairs = ArrayList<PAIR<TextView, String>>().apply {
            add(PAIR(tvSqbm, Key.sqbm_input))
            add(PAIR(tvSqr, Key.sqr_input))
            add(PAIR(tvSqsj, Key.sqsj_input))
            add(PAIR(tvLyxq, Key.lyxq_input))
            add(PAIR(tvLyrq, Key.lyrq_date))
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
        findViewById<View>(R.id.tvSblyDetail).safeClicks().subscribe {
            context.startActivity(Intent(context, SblyDetailAct::class.java))
        }
        if (nodeId in listOf("OA_FLOW_XZZB_SBLY_CGR", "OA_FLOW_XZZB_SBLY_BGS", "OA_FLOW_XZZB_SBLY_KGY"))
            tvLyrq.clickDate()
    }

    override fun saveToSpd(spd: Spd) {
        pairs.forEach {
            it.apply {
                spd.set(key, textView.trimText())
            }
        }
    }

}