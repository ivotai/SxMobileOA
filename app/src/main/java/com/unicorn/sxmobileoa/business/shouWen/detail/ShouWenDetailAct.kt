package com.unicorn.sxmobileoa.business.shouWen.detail

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.business.general.flowNode.FlowNodeAct

class ShouWenDetailAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_shou_wen_detail)

        Intent(this,FlowNodeAct::class.java).let(this::startActivity)
    }
}
