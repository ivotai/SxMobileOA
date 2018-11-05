package com.unicorn.sxmobileoa.header.gcsq

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
class GcsqInfoView(context: Context, menu: Menu, spd: Spd) : FrameLayout(context),
        BasicInfoView {

    init {
        initViews(context, menu, spd)
    }

    lateinit var tvTitle: TextView
    lateinit var tvBt: TextView
    lateinit var tvSqr: TextView
    lateinit var tvSzbm: TextView
    lateinit var tvWcr: TextView
    lateinit var tvFzbry: TextView
    lateinit var tvWcdd: TextView
    lateinit var tvWcsy: TextView
    lateinit var tvXjzl: TextView
    lateinit var tvKsqr: TextView
    lateinit var tvJsqr: TextView
    lateinit var tvBz: TextView

    private lateinit var pairs: ArrayList<PAIR<TextView, String>>

    fun initViews(context: Context, menu: Menu, spd: Spd) {
        LayoutInflater.from(context).inflate(R.layout.header_view_gcsq, this, true)
        findView()
        renderView(menu, spd)
//        canEdit(dbxx)
    }

    private fun findView() {
        tvTitle = findViewById(R.id.tvTitle)
        tvBt = findViewById(R.id.tvBt)
        tvSqr = findViewById(R.id.tvSqr)
        tvSzbm = findViewById(R.id.tvSzbm)
        tvWcr = findViewById(R.id.tvWcr)
        tvFzbry = findViewById(R.id.tvFzbry)
        tvWcdd = findViewById(R.id.tvWcdd)
        tvWcsy = findViewById(R.id.tvWcsy)
        tvKsqr = findViewById(R.id.tvKsrq)
        tvJsqr = findViewById(R.id.tvJsrq)
        tvBz = findViewById(R.id.tvBz)

        // 把 textView 和对应 key 放入 pair
        pairs = ArrayList<PAIR<TextView, String>>().apply {
            add(PAIR(tvSqr, Key.sqr_input))
            add(PAIR(tvSzbm, Key.szbm_input))
            add(PAIR(tvWcr, Key.mcwcr_input))
            add(PAIR(tvFzbry, Key.fzbry_input))
            add(PAIR(tvWcdd, Key.wcdd_input))
            add(PAIR(tvWcsy, Key.wcsy_textarea))
            add(PAIR(tvBz, Key.bz_textarea))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun renderView(menu: Menu, spd: Spd) {
        tvTitle.text = "${Global.court!!.dmms}${menu.text}"
        tvBt.text = spd.spdXx.bt
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
            pairs.forEach {
                it.apply {
                    textView.isEnabled = true
                }
            }
            tvWcr.clickDeptUser(Key.textResult, Key.mcwcr_input)
            tvKsqr.clickDate()
            tvJsqr.clickDate()
            RxBus.get().registerEvent(TextResult::class.java, context as LifecycleOwner, Consumer { textResult ->
                tvWcr.text = textResult.result
            })
        }
        tvBz.isEnabled = nodeId in listOf("OA_FLOW_QJGL_GCGL_RSCBA", "OA_FLOW_QJGL_QJGL_RSCLDSP")
    }

    override fun saveToSpd(spd: Spd) :Boolean{
        pairs.forEach {
            it.apply {
                spd.set(key, textView.trimText())
            }
        }
        spd.spdXx.column3 = tvKsqr.trimText()
        spd.spdXx.column4 = tvJsqr.trimText()
        return true
    }

}