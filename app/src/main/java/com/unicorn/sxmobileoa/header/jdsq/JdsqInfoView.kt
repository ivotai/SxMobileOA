package com.unicorn.sxmobileoa.header.jdsq

import android.annotation.SuppressLint
import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.*
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.mess.SpdHelper
import com.unicorn.sxmobileoa.app.mess.model.TextResult
import com.unicorn.sxmobileoa.header.BasicInfoView
import com.unicorn.sxmobileoa.header.PAIR
import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.model.Spd
import io.reactivex.functions.Consumer

@SuppressLint("ViewConstructor")
class JdsqInfoView(context: Context, menu: Menu, dbxx: Dbxx, spd: Spd) : FrameLayout(context), BasicInfoView {

    init {
        initViews(context, menu, spd)
    }

    lateinit var tvTitle: TextView
    lateinit var tvBt: TextView
    lateinit var tvSqr: TextView
    lateinit var tvSqrq: TextView
    lateinit var tvCbbm: TextView
    lateinit var tvJcdd: TextView
    lateinit var tvCbr: TextView
    lateinit var tvJdrq: TextView
    lateinit var tvLfkrdw: TextView
    lateinit var tvXm: TextView
    lateinit var tvLfrs: TextView
    lateinit var tvJb: TextView
    lateinit var tvPtry: TextView
    lateinit var tvLfsy: TextView
    lateinit var tvJdje: TextView
    lateinit var tvBz: TextView

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
        tvCbbm = findViewById(R.id.tvCbbm)
        tvJcdd = findViewById(R.id.tvJcdd)
        tvCbr = findViewById(R.id.tvCbr)
        tvJdrq = findViewById(R.id.tvJdrq)
        tvLfkrdw = findViewById(R.id.tvLfkrdw)
        tvXm = findViewById(R.id.tvXm)
        tvLfrs = findViewById(R.id.tvLfrs)
        tvJb = findViewById(R.id.tvJb)
        tvPtry = findViewById(R.id.tvPtry)
        tvLfsy = findViewById(R.id.tvLfsy)
        tvJdje = findViewById(R.id.tvJdje)
        tvBz = findViewById(R.id.tvBz)

        // 保存 textView 和 key
        pairs = ArrayList<PAIR<TextView, String>>().apply {
            add(PAIR(tvSqr, Key.sqr_input))
            add(PAIR(tvSqrq, Key.sqrq_input))
            add(PAIR(tvCbbm, Key.cbbmmc_input))
            add(PAIR(tvJcdd, Key.jcdd_select))
            add(PAIR(tvCbr, Key.cbr_input))
            add(PAIR(tvJdrq, Key.jdrq_input))
            add(PAIR(tvLfkrdw, Key.lfkrdw_input))
            add(PAIR(tvXm, Key.xm_input))
            add(PAIR(tvLfrs, Key.lfrs_input))
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

        // 遍历展示
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
            tvCbbm.clickDept(Key.cbbmmc_input)
            tvJcdd.clickCode("就餐地点","JDGL_JDJLSQ_JCDD",Key.jcdd_select)
            tvCbr.clickDeptUser(Key.textResult, null)
            tvJdrq.clickDate()
            tvJb.clickCode("级别","JDGL_JDJLSQ_JB",Key.jb_select)
            tvPtry.clickDeptUser(Key.textResult, null)
            RxBus.get().registerEvent(TextResult::class.java, context as LifecycleOwner, Consumer { textResult ->
                when (textResult.key) {
                    Key.cbbmmc_input -> tvCbbm
                    Key.jcdd_select -> tvJcdd
                    Key.cbr_input -> tvCbr
                    Key.jdrq_input -> tvJdrq
                    Key.jb_select -> tvJb
                    else -> tvPtry
                }.text = textResult.result
            })
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