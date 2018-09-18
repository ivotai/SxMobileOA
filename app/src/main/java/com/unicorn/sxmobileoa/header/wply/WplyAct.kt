package com.unicorn.sxmobileoa.header.wply

import android.content.Intent
import com.unicorn.sxmobileoa.select.code.ui.CodeAct
import com.unicorn.sxmobileoa.spd.ui.SpdAct

class WplyAct : SpdAct() {

    override fun addBasicHeaderView() = WplyHeaderView(this, model.menu, model.dbxx, spd).apply {
        context.startActivity(Intent(context, CodeAct::class.java).apply {
//            putExtra(Key.title, title)
//            putExtra(Key.code, code)
//            putExtra(Key.key, key)
        })
        flowNodeAdapter.addHeaderView(this)
    }

}