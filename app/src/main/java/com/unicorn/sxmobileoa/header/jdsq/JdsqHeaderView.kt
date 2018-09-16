package com.unicorn.sxmobileoa.header.jdsq

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.get
import com.unicorn.sxmobileoa.header.BasicHeaderView
import com.unicorn.sxmobileoa.header.PAIR
import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.model.Spd

@SuppressLint("ViewConstructor")
class JdsqHeaderView(context: Context, menu: Menu, dbxx: Dbxx, spd: Spd) : FrameLayout(context), BasicHeaderView {

    init {
        initViews(context, menu, spd)
    }

    lateinit var tvTitle: TextView
    lateinit var tvBt: TextView

    lateinit var tvSqr: TextView
    lateinit var tvSqrq: TextView
    lateinit var tvJcdd: TextView
    lateinit var tvCbr: TextView

    private lateinit var pairs: ArrayList<PAIR<TextView, String>>

    fun initViews(context: Context, menu: Menu, spd: Spd) {
        LayoutInflater.from(context).inflate(R.layout.header_view_jdsq, this, true)
        findView()
        renderView(menu, spd)
        canEdit(spd)
    }

    private fun findView() {
        tvTitle = findViewById(R.id.tvTitle)
        tvBt = findViewById(R.id.tvBt)

        tvSqr = findViewById(R.id.tvSqr)
        tvSqrq = findViewById(R.id.tvSqrq)
        tvJcdd = findViewById(R.id.tvJcdd)
        tvCbr = findViewById(R.id.tvCbr)

        // 保存 textView 和 eky
        pairs = ArrayList<PAIR<TextView, String>>().apply {
            add(PAIR(tvSqr, Key.sqr_input))
            add(PAIR(tvSqrq, Key.sqrq_input))
            add(PAIR(tvJcdd, Key.jcdd_input))
            add(PAIR(tvCbr, Key.cbr_input))

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

    }

    override fun saveToSpd(spd: Spd) {
        // do nothing
    }

}