package com.unicorn.sxmobileoa.header.qjsq

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
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

@SuppressLint("ViewConstructor")
class QjsqHeaderView(context: Context, menu: Menu, dbxx: Dbxx, spd: Spd) : FrameLayout(context),
        BasicHeaderView {

    init {
        initViews(context, menu, dbxx, spd)
    }

    lateinit var tvTitle: TextView  // 内部发文标题
    lateinit var tvBt: TextView     // 真的标题
    lateinit var tvSqrq: TextView

    lateinit var tvQjr: TextView
    lateinit var tvZw: TextView
    lateinit var tvSzbm: TextView
    lateinit var tvQjsy: TextView
    lateinit var tvQjzl: TextView

    lateinit var tvKsqr: TextView
    lateinit var tvJsqr: TextView

    lateinit var tvBz: TextView

    private lateinit var pairs: ArrayList<PAIR<TextView, String>>

    fun initViews(context: Context, menu: Menu, dbxx: Dbxx, spd: Spd) {
        LayoutInflater.from(context).inflate(R.layout.header_view_sqrq, this, true)
        findView()
        renderView(menu, spd)
        canEdit(dbxx)
    }

    private fun findView() {
        tvTitle = findViewById(R.id.tvTitle)
        tvBt = findViewById(R.id.tvBt)
        tvSqrq = findViewById(R.id.tvSqrq)

        tvQjr = findViewById(R.id.tvQjr)
        tvZw = findViewById(R.id.tvZw)
        tvSzbm = findViewById(R.id.tvSzbm)
        tvQjsy = findViewById(R.id.tvQjsy)
        tvQjzl = findViewById(R.id.tvQjzl)

        tvKsqr = findViewById(R.id.tvKsrq)
        tvJsqr = findViewById(R.id.tvJsrq)

        tvBt = findViewById(R.id.tvBz)

        // 把 textView 和对应 key 放入 pair
        pairs = ArrayList<PAIR<TextView, String>>().apply {
            add(PAIR(tvQjr, Key.qjr_input))
            add(PAIR(tvZw, Key.zw_input))
            add(PAIR(tvSzbm, Key.szbm_input))
            add(PAIR(tvQjsy, Key.qjsy_textarea))
            add(PAIR(tvQjzl, Key.qjdlx))
            add(PAIR(tvBt, Key.bz_textarea))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun renderView(menu: Menu, spd: Spd) {
        tvTitle.text = "${Global.court!!.dmms}${menu.text}"
        tvBt.text = spd.spdXx.bt
        tvSqrq.text = spd.spdXx.column2
        tvKsqr.text = spd.spdXx.column3
        tvJsqr.text = spd.spdXx.column4

        // 遍历展示
        pairs.forEach {
            it.apply {
                textView.text = spd.get(key)
            }
        }
    }

    private fun canEdit(dbxx: Dbxx) {
        val nodeId = dbxx.param.nodeId
        if (SpdHelper().canEdit2(nodeId)) {
            // 遍历，使其可编辑
            pairs.forEach {
                it.apply {
                    textView.isEnabled = true
                }
            }
        } else if (nodeId in listOf("OA_FLOW_QJGL_GCGL_RSCBA", "OA_FLOW_QJGL_QJGL_RSCLDSP")) {
            tvBz.isEnabled = true
        }
    }

    override fun saveToSpd(spd: Spd) {
        pairs.forEach {
            it.apply {
                spd.set(key, textView.trimText())
            }
        }
    }

}