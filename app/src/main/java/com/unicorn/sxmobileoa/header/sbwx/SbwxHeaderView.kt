package com.unicorn.sxmobileoa.header.sbwx

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.*
import com.unicorn.sxmobileoa.header.BasicHeaderView
import com.unicorn.sxmobileoa.header.PAIR
import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.helper.SpdHelper
import com.unicorn.sxmobileoa.spd.model.Spd

@SuppressLint("ViewConstructor")
class SbwxHeaderView(context: Context, menu: Menu, dbxx: Dbxx, spd: Spd) : FrameLayout(context),
        BasicHeaderView {

    init {
        initViews(context, menu, dbxx, spd)
    }

    lateinit var tvTitle: TextView  // 内部发文标题
    lateinit var tvBt: TextView     // 真的标题

    lateinit var tvJbdw: TextView   // 不可编辑
    lateinit var tvJbr: TextView    // 不可编辑
    lateinit var tvSj: TextView
    lateinit var tvWxxq: TextView    // 维修详情
    lateinit var tvWxff: TextView    // 维修方法
    lateinit var tvWxysjf: TextView    // 预算经费
    lateinit var tvCljg: TextView    // 预算经费

    private lateinit var pairs: ArrayList<PAIR<TextView, String>>

    fun initViews(context: Context, menu: Menu, dbxx: Dbxx, spd: Spd) {
        LayoutInflater.from(context).inflate(R.layout.header_view_sbwx, this, true)
        findView()
        renderView(menu, spd)
        canEdit(dbxx)
    }

    private fun findView() {
        tvTitle = findViewById(R.id.tvTitle)
        tvBt = findViewById(R.id.tvBt)

        tvJbdw = findViewById(R.id.tvJbdw)
        tvJbr = findViewById(R.id.tvJbr)
        tvSj = findViewById(R.id.tvSj)
        tvWxxq = findViewById(R.id.tvWxxq)
        tvWxff = findViewById(R.id.tvWxff)
        tvWxysjf = findViewById(R.id.tvWxysjf)
        tvCljg = findViewById(R.id.tvCljg)

        // 把 textView 和对应 key 放入 pair
        pairs = ArrayList<PAIR<TextView, String>>().apply {
            add(PAIR(tvJbdw, Key.jbdw_input))
            add(PAIR(tvJbr, Key.jbr_input))
            add(PAIR(tvSj, Key.sj_input))
            add(PAIR(tvWxxq, Key.wxxq_textarea))
            add(PAIR(tvWxff, Key.wxff_textarea))
            add(PAIR(tvWxysjf, Key.wxysjf_input))
            add(PAIR(tvCljg, Key.cljg_input))
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
        if (SpdHelper().canEdit2(nodeId)) {
            tvCljg.isEnabled = true
            return
        }
        //
        val list = listOf(
                "OA_FLOW_XZZB_SBWX_XXZXYJ",
                "OA_FLOW_XZZB_SBWX_XXZXBL",
                "OA_FLOW_XZZB_SBWX_WXRY"
        )
        if (nodeId in list) {
            tvCljg.isEnabled = true
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