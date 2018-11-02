package com.unicorn.sxmobileoa.header.sbbf

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.get
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.header.BasicInfoView
import com.unicorn.sxmobileoa.header.PAIR
import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.model.Spd

@SuppressLint("ViewConstructor")
class SbbfInfoView(context: Context, menu: Menu, dbxx: Dbxx, spd: Spd) : FrameLayout(context), BasicInfoView {

    init {
        initViews(context, menu, dbxx, spd)
    }

    lateinit var tvTitle: TextView
    lateinit var tvBt: TextView         // 真的标题
    lateinit var tvSqbm: TextView
    lateinit var tvSqr: TextView
    lateinit var tvSqsj: TextView
    lateinit var tvBfxq: TextView

    private lateinit var pairs: ArrayList<PAIR<TextView, String>>

    fun initViews(context: Context, menu: Menu, dbxx: Dbxx, spd: Spd) {
        LayoutInflater.from(context).inflate(R.layout.header_view_sbbf, this, true)
        findView()
        renderView(menu, spd)
        canEdit(spd)
    }

    private fun findView() {
        tvTitle = findViewById(R.id.tvTitle)
        tvBt = findViewById(R.id.tvBt)

        tvSqbm = findViewById(R.id.tvSqbm)
        tvSqr = findViewById(R.id.tvSqr)
        tvSqsj = findViewById(R.id.tvSqsj)
        tvBfxq = findViewById(R.id.tvBfxq)

        // 保存 textView 和 eky
        pairs = ArrayList<PAIR<TextView, String>>().apply {
            add(PAIR(tvSqbm, Key.sqbm_input))
            add(PAIR(tvSqr, Key.sqr_input))
            add(PAIR(tvSqsj, Key.sqsj_input))
            add(PAIR(tvBfxq, Key.bfxq_input))
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

    private fun canEdit(spd: Spd) {
        findViewById<View>(R.id.tvSbbfDetail).safeClicks().subscribe {
            context.startActivity(Intent(context, SbbfDetailAct::class.java))
        }
    }

    override fun saveToSpd(spd: Spd):Boolean {
        // do nothing
        return true
    }

}