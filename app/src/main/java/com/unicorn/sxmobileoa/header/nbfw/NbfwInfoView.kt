package com.unicorn.sxmobileoa.header.nbfw

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
class NbfwInfoView(context: Context, menu: Menu, spd: Spd) : FrameLayout(context), BasicInfoView {

    init {
        initViews(context, menu, spd)
    }

    lateinit var tvTitle: TextView
    lateinit var tvBt: TextView     // 真的标题

    lateinit var tvJbbm: TextView
    lateinit var tvNgr: TextView
    lateinit var tvZsmc: TextView
    lateinit var tvCsmc: TextView

    private lateinit var pairs: ArrayList<PAIR<TextView, String>>

    fun initViews(context: Context, menu: Menu, spd: Spd) {
        LayoutInflater.from(context).inflate(R.layout.header_view_nbfw, this, true)
        findView()
        renderView(menu, spd)
//        canEdit(dbxx)
    }

    private fun findView() {
        tvTitle = findViewById(R.id.tvTitle)
        tvBt = findViewById(R.id.tvBt)

        tvJbbm = findViewById(R.id.tvJbbm)
        tvNgr = findViewById(R.id.tvNgr)
        tvZsmc = findViewById(R.id.tvZsmc)
        tvCsmc = findViewById(R.id.tvCsmc)

        // 把 textView 和对应 key 放入 pair
        pairs = ArrayList<PAIR<TextView, String>>().apply {
            add(PAIR(tvJbbm, Key.jbbm_input))
            add(PAIR(tvNgr, Key.ngr_input))
            add(PAIR(tvZsmc, Key.zsmc_input))
            add(PAIR(tvCsmc, Key.csmc_input))
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
        if (SpdHelper().canEdit2(dbxx.param.nodeId)) {
            tvZsmc.clickDept(Key.zsmc_input)
            tvCsmc.clickDept(Key.csmc_input)
            RxBus.get().registerEvent(TextResult::class.java, context as LifecycleOwner, Consumer { textResult ->
                when (textResult.key) {
                    Key.zsmc_input -> tvZsmc
                    else -> tvCsmc
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