package com.unicorn.sxmobileoa.header.ycsq

import android.annotation.SuppressLint
import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.content.Intent
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
import com.unicorn.sxmobileoa.header.ycsq.cllx.ui.CllxAct
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.model.Spd
import io.reactivex.functions.Consumer
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.header_view_ycsq.view.*

@SuppressLint("ViewConstructor")
class YcsqInfoView(context: Context, menu: Menu, spd: Spd) : FrameLayout(context), BasicInfoView, LayoutContainer {

    override val containerView = this

    private lateinit var pairs: ArrayList<PAIR<TextView, String>>

    init {
        initViews(context, menu, spd)
    }

    fun initViews(context: Context, menu: Menu, spd: Spd) {
        LayoutInflater.from(context).inflate(R.layout.header_view_ycsq, this, true)
        findView()
        preparePairs(menu, spd)
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
            add(PAIR(tvCcsj1, Key.ccsjxm1_input))
            add(PAIR(tvCcsj2, Key.ccsjxm2_input))
            add(PAIR(tvCcsj3, Key.ccsjxm3_input))
            add(PAIR(tvSycl1, Key.syclcp1_input))
            add(PAIR(tvSycl2, Key.syclcp2_input))
            add(PAIR(tvSycl3, Key.syclcp3_input))
            add(PAIR(tvPcsj, Key.pcsj_input))
            add(PAIR(tvHzsj, Key.hzsj_date))
            add(PAIR(tvBcsm, Key.bcsm_input))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun preparePairs(menu: Menu, spd: Spd) {
        tvTitle.text = "${Global.court!!.dmms}${menu.text}"
        spd.spdXx.apply {
            tvBt.text = bt
            tvYcsy.setText(column1)
            tvKwdd.setText(column3)
            tvPcr.text = column8
        }
        pairs.forEach { pair ->
            pair.apply {
                textView.text = spd.get(key)
            }
        }
    }

    private fun canEdit(spd: Spd) {
        val nodeId = spd.nodeModel_1!!.nodeid
        if (SpdHelper().canEdit2(nodeId)) {
            pairs.forEach {
                it.apply {
                    textView.isEnabled = true
                }
            }
            tvYcsy.isEnabled = true

            // TODO TIME
            tvCfsj.clickDate()
            tvFhsj.clickDate()

            tvCcrmc.clickDeptUser(Key.textResult, Key.ccrmc_input)
            tvCllx.safeClicks().subscribe {
                context.startActivity(Intent(context, CllxAct::class.java).apply {
                    putExtra(Key.key, Key.cllx_input)
                })
            }
            tvKwdd.isEnabled = true
        }

        val flag = nodeId in listOf(
                "OA_FLOW_HQGL_YCSQ_CGSP",
                "OA_FLOW_HQGL_YCSQ_CDSP",
                "OA_FLOW_HQGL_YCSQ_CDPC",
                "OA_FLOW_HQGL_YCSQ_CGK",
                "OA_FLOW_HQGL_YCSQ_BGSSP",
                "OA_FLOW_HQGL_YCSQ_CDDZ",
                "OA_FLOW_HQGL_YCSQ_TZ",
                "OA_FLOW_HQGL_YCSQ_FYZSP"
        )
        if (flag) {
            tvCcsj1.clickCode("选择出车司机", "YCSQ_SJ", Key.ccsj1_select)
            tvCcsj2.clickCode("选择出车司机", "YCSQ_SJ", Key.ccsj2_select)
            tvCcsj3.clickCode("选择出车司机", "YCSQ_SJ", Key.ccsj3_select)
            tvSycl1.clickCode("选择使用车辆", "YCSQ_CL", Key.sycl1_select)
            tvSycl2.clickCode("选择使用车辆", "YCSQ_CL", Key.sycl2_select)
            tvSycl3.clickCode("选择使用车辆", "YCSQ_CL", Key.sycl3_select)
            tvPcsj.clickDate()
            tvHzsj.clickDate()
        }

        //
        RxBus.get().registerEvent(TextResult::class.java, context as LifecycleOwner, Consumer {
            when (it.key) {
                Key.ccrmc_input -> tvCcrmc
                Key.ccsj1_select -> tvCcsj1
                Key.ccsj2_select -> tvCcsj2
                Key.ccsj3_select -> tvCcsj3
                Key.sycl1_select -> tvSycl1
                Key.sycl2_select -> tvSycl2
                Key.sycl3_select -> tvSycl3
                else -> tvCllx
            }.text = it.result
        })
    }

    override fun saveToSpd(spd: Spd): Boolean {
        spd.spdXx.column1 = tvYcsy.trimText()
        spd.spdXx.column3 = tvKwdd.trimText()


        // column4 司机总信息
        listOf(tvCcsj1, tvCcsj2, tvCcsj3)
                .map { it.trimText() }
                .map { it.split("--")[0] }
                .filter { it != "" }
                .joinToString(",") { it }
                .let {
                    spd.spdXx.column4 = it
                }
        // column5 车辆总信息
        listOf(tvSycl1, tvSycl2, tvSycl3)
                .map { it.trimText() }
                .filter { it != "" }
                .joinToString(",") { it }
                .let {
                    spd.spdXx.column5 = it
                }

        pairs.forEach {
            it.apply {
                spd.set(key, textView.trimText())
            }
        }
        return true
    }

}