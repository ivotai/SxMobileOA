package com.unicorn.sxmobileoa.header.ycsq

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.*
import com.unicorn.sxmobileoa.app.mess.SpdHelper
import com.unicorn.sxmobileoa.header.BasicHeaderView
import com.unicorn.sxmobileoa.header.PAIR
import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.model.Spd

@SuppressLint("ViewConstructor")
class YcsqHeaderView(context: Context, menu: Menu, dbxx: Dbxx, spd: Spd) : FrameLayout(context), BasicHeaderView {

    init {
        initViews(context, menu, spd)
    }

    @BindView(R.id.tvTitle)
    lateinit var tvTitle: TextView
    @BindView(R.id.tvBt)
    lateinit var tvBt: TextView
    @BindView(R.id.tvYcsy)
    lateinit var tvYcsy: TextView
    @BindView(R.id.tvHbmc)
    lateinit var tvHbmc: TextView
    @BindView(R.id.tvSqr)
    lateinit var tvSqr: TextView
    @BindView(R.id.tvSqrdh)
    lateinit var tvSqrdh: TextView
    @BindView(R.id.tvCfsj)
    lateinit var tvCfsj: TextView
    @BindView(R.id.tvFhsj)
    lateinit var tvFhsj: TextView
    @BindView(R.id.tvCcrmc)
    lateinit var tvCcrmc: TextView
    @BindView(R.id.tvCllx)
    lateinit var tvCllx: TextView
    @BindView(R.id.tvKwdd)
    lateinit var tvKwdd: TextView
    @BindView(R.id.tvYcrs)
    lateinit var tvYcrs: TextView
    @BindView(R.id.tvCcsj1)
    lateinit var tvCcsj1: TextView
    @BindView(R.id.tvCcsj2)
    lateinit var tvCcsj2: TextView
    @BindView(R.id.tvCcsj3)
    lateinit var tvCcsj3: TextView
    @BindView(R.id.tvSycl1)
    lateinit var tvSycl1: TextView
    @BindView(R.id.tvSycl2)
    lateinit var tvSycl2: TextView
    @BindView(R.id.tvSycl3)
    lateinit var tvSycl3: TextView
    @BindView(R.id.tvPcr)
    lateinit var tvPcr: TextView
    @BindView(R.id.tvPcsj)
    lateinit var tvPcsj: TextView
    @BindView(R.id.tvHzsj)
    lateinit var tvHzsj: TextView
    @BindView(R.id.tvBcsm)
    lateinit var tvBcsm: TextView

    private lateinit var pairs: ArrayList<PAIR<TextView, String>>

    fun initViews(context: Context, menu: Menu, spd: Spd) {
        val view = LayoutInflater.from(context).inflate(R.layout.header_view_ycsq, this, true)
        ButterKnife.bind(this, view)
        findView()
        renderView(menu, spd)
        canEdit(spd)
    }

    private fun findView() {
        pairs = ArrayList<PAIR<TextView, String>>().apply {
            add(PAIR(tvHbmc, Key.hbmc_input))
            add(PAIR(tvSqr, Key.sqr_input))
            add(PAIR(tvSqrdh, Key.sqrdh_input))
            add(PAIR(tvCfsj, Key.cfsj_input))
            add(PAIR(tvFhsj, Key.fhsj_input))
            add(PAIR(tvCcrmc, Key.ccrmc_input))
            add(PAIR(tvCllx, Key.cllx_input))
            add(PAIR(tvYcrs, Key.ycrs_input))
            add(PAIR(tvCcsj1, Key.ccsj1_select))
            add(PAIR(tvCcsj2, Key.ccsj2_select))
            add(PAIR(tvCcsj3, Key.ccsj3_select))
            add(PAIR(tvSycl1, Key.sycl1_select))
            add(PAIR(tvSycl2, Key.sycl2_select))
            add(PAIR(tvSycl3, Key.sycl3_select))
            add(PAIR(tvPcsj, Key.pcsj_input))
            add(PAIR(tvHzsj, Key.hzsj_date))
            add(PAIR(tvBcsm, Key.bcsm_input))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun renderView(menu: Menu, spd: Spd) {
        tvTitle.text = "${Global.court!!.dmms}${menu.text}"
        tvBt.text = spd.spdXx.bt
        tvYcsy.text = spd.spdXx.column1
        tvKwdd.text = spd.spdXx.column3
        tvPcr.text = spd.spdXx.column8
        pairs.forEach {
            it.apply {
                textView.text = spd.get(key)
            }
        }
    }

    private fun canEdit(spd: Spd) {
        val nodeId = spd.spdXx.nodeId
        if (SpdHelper().canEdit2(nodeId)) {
            pairs.forEach {
                it.apply {
                    textView.isEnabled = true
                }
            }
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