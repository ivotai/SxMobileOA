package com.unicorn.sxmobileoa.header.qjsq

import android.annotation.SuppressLint
import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.*
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.mess.model.TextResult
import com.unicorn.sxmobileoa.header.BasicInfoView
import com.unicorn.sxmobileoa.header.PAIR
import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.model.Spd
import io.reactivex.functions.Consumer
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.header_view_qjsq.view.*

@SuppressLint("ViewConstructor")
class QjsqInfoView(context: Context, menu: Menu, dbxx: Dbxx, spd: Spd, isAdd: Boolean = false) : FrameLayout(context), BasicInfoView, LayoutContainer {

    override val containerView = this

    private lateinit var pairs: ArrayList<PAIR<TextView, String>>

    init {
        initViews(context, menu, dbxx, spd, isAdd)
    }

    fun initViews(context: Context, menu: Menu, dbxx: Dbxx, spd: Spd, isAdd: Boolean) {
        LayoutInflater.from(context).inflate(R.layout.header_view_qjsq, this, true)
        preparePairs()
        renderView(menu, spd)
        canEdit(dbxx, isAdd)
    }

    private fun preparePairs() {
        pairs = ArrayList<PAIR<TextView, String>>().apply {
            add(PAIR(tvQjr, Key.qjr_input))
            add(PAIR(tvZw, Key.zw_input))
            add(PAIR(tvSzbm, Key.szbm_input))
            add(PAIR(tvQjsy, Key.qjsy_textarea))
            add(PAIR(tvXjzl, Key.xjzljsy_select))
            add(PAIR(tvBz, Key.bz_textarea))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun renderView(menu: Menu, spd: Spd) {
        tvTitle.text = "${Global.court!!.dmms}${menu.text}"
        tvBt.text = spd.spdXx.bt
        tvSqrq.text = spd.spdXx.column2
        tvKsrq.text = spd.spdXx.column3
        tvJsrq.text = spd.spdXx.column4
        pairs.forEach { pair ->
            pair.apply {
                textView.text = spd.get(key)
            }
        }
    }

    private fun canEdit(dbxx: Dbxx, isAdd: Boolean) {
        if (true) {
            tvBt.isEnabled = true
            tvSqrq.clickDate()
            tvZw.isEnabled = true
            tvQjsy.isEnabled = true
            tvXjzl.clickCode("休假种类及年度", "QJSQ_JQ", Key.xjzljsy_select)
            tvKsrq.clickDate()
            tvJsrq.clickDate()
            RxBus.get().registerEvent(TextResult::class.java, context as LifecycleOwner, Consumer { textResult ->
                tvXjzl.text = textResult.result
            })
        }

        // 特殊字段
        val nodeId = dbxx.param.nodeId
        tvBz.isEnabled = nodeId in listOf("OA_FLOW_QJGL_GCGL_RSCBA", "OA_FLOW_QJGL_QJGL_RSCLDSP")
    }

    override fun saveToSpd(spd: Spd) {
        spd.spdXx.column2 = tvSqrq.trimText()
        spd.spdXx.column3 = tvKsrq.trimText()
        spd.spdXx.column4 = tvJsrq.trimText()
        pairs.forEach { pair ->
            pair.apply {
                spd.set(key, textView.trimText())
            }
        }
    }

}