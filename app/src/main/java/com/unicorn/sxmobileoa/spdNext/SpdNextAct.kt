package com.unicorn.sxmobileoa.spdNext

import android.support.v7.widget.LinearLayoutManager
import com.orhanobut.logger.Logger
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.addDefaultItemDecoration
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.model.Spd
import com.unicorn.sxmobileoa.spdNext.network.SpdNext
import kotlinx.android.synthetic.main.act_spd_next.*

class SpdNextAct : BaseAct() {

    override val layoutId = R.layout.act_spd_next

    lateinit var menu: Menu

    lateinit var dbxx: Dbxx
    lateinit var spd: Spd

    override fun initArguments() {
        menu = intent.getSerializableExtra(Key.menu) as Menu
        dbxx = intent.getSerializableExtra(Key.dbxx) as Dbxx
        spd = intent.getSerializableExtra(Key.spd) as Spd
    }


    val adapter1 = Adapter1()
    val adapter2 = Adapter1()

    override fun initViews() {
        recyclerView1.apply {
            layoutManager = LinearLayoutManager(this@SpdNextAct)
            adapter1.bindToRecyclerView(this)
            addDefaultItemDecoration()
        }
        recyclerView2.apply {
            layoutManager = LinearLayoutManager(this@SpdNextAct)
            adapter2.bindToRecyclerView(this)
            addDefaultItemDecoration()
        }
    }

    override fun bindIntent() {
        adapter1.setNewData(listOf(1, 2, 3, 5, 6, 7, 8))
        adapter2.setNewData(listOf(1, 2, 3, 5, 6, 7, 8))

        SpdNext(menu,dbxx,spd).toMaybe(this).subscribe {
            Logger.e(it.toString())
        }
    }


}
