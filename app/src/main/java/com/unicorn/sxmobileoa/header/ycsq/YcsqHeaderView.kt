package com.unicorn.sxmobileoa.header.ycsq

import android.annotation.SuppressLint
import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.*
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.mess.SpdHelper
import com.unicorn.sxmobileoa.app.mess.model.TextResult
import com.unicorn.sxmobileoa.header.BasicHeaderView
import com.unicorn.sxmobileoa.header.PAIR
import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.model.Spd
import io.reactivex.functions.Consumer

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
    lateinit var tvJb: TextView
    lateinit var tvPtry: TextView
    lateinit var tvLfsy: TextView
    lateinit var tvJdje: TextView
    lateinit var tvBz: TextView

    private lateinit var pairs: ArrayList<PAIR<TextView, String>>

    fun initViews(context: Context, menu: Menu, spd: Spd) {
        val view = LayoutInflater.from(context).inflate(R.layout.header_view_ycsq, this, true)
        ButterKnife.bind(this, view)
        findView()
        renderView(menu, spd)
        canEdit(spd)
    }

    private fun findView() {
        tvJb = findViewById(R.id.tvJb)
        tvPtry = findViewById(R.id.tvPtry)
        tvLfsy = findViewById(R.id.tvLfsy)
        tvJdje = findViewById(R.id.tvJdje)
        tvBz = findViewById(R.id.tvBz)

        pairs = ArrayList<PAIR<TextView, String>>().apply {
            add(PAIR(tvHbmc, Key.hbmc_input))
            add(PAIR(tvSqr, Key.sqr_input))
            add(PAIR(tvSqrdh, Key.sqrdh_input))
            add(PAIR(tvCfsj, Key.cfsj_input))
            add(PAIR(tvFhsj, Key.fhsj_input))
            add(PAIR(tvCcrmc, Key.ccrmc_input))
            add(PAIR(tvCllx, Key.cllx_input))
            add(PAIR(tvYcrs, Key.ycrs_input))

            add(PAIR(tvJb, Key.jb_select))
            add(PAIR(tvPtry, Key.ptry_input))
            add(PAIR(tvLfsy, Key.lfsy_textarea))
            add(PAIR(tvJdje, Key.jdje_input))
            add(PAIR(tvBz, Key.bz_textarea))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun renderView(menu: Menu, spd: Spd) {
        tvTitle.text = "${Global.court!!.dmms}${menu.text}"
        tvBt.text = spd.spdXx.bt
        tvYcsy.text = spd.spdXx.column1
        tvKwdd.text = spd.spdXx.column3
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
            tvJb.clickCode("级别", "JDGL_JDJLSQ_JB", Key.jb_select)
            tvPtry.clickDeptUser(Key.textResult, null)
            RxBus.get().registerEvent(TextResult::class.java, context as LifecycleOwner, Consumer { textResult ->

                when (textResult.key) {
                    Key.jb_select -> tvJb
                    else -> tvPtry
                }.text = textResult.result
            })
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