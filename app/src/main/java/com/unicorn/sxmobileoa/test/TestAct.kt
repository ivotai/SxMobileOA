package com.unicorn.sxmobileoa.test

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.unicorn.sxmobileoa.R
import kotlinx.android.synthetic.main.act_test.*

class TestAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_test)

        viewPager.adapter = TestPagerAdapter(supportFragmentManager)
    }

}
