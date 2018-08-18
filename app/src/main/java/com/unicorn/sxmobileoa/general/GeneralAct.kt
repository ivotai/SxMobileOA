package com.unicorn.sxmobileoa.general

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.unicorn.sxmobileoa.R
import kotlinx.android.synthetic.main.act_general.*

class GeneralAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_general)

        tvTitle.text = "收文"
        viewPager.adapter = GeneralPagerAdapter(supportFragmentManager)
    }

}
