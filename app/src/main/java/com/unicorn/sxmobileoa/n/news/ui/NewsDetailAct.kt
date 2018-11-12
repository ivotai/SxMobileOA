package com.unicorn.sxmobileoa.n.news.ui

import android.annotation.SuppressLint
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.n.news.network.GetDetail
import kotlinx.android.synthetic.main.act_news_detail.*

class NewsDetailAct : BaseAct() {

    override val layoutId = R.layout.act_news_detail

    override fun initViews() {
        titleBar.setTitle("新闻详情")
    }

    override fun bindIntent() {
        getDetail()
    }

    @SuppressLint("CheckResult")
    private fun getDetail() {
        val contentId = intent.getStringExtra(Key.contentId)
        GetDetail(contentId).toMaybe(this).subscribe {
            tvTitle2.text = it.title
            tvDate.text = it.release_date.split(" ")[0]
            webView.loadDataWithBaseURL(null, it.txt, "text/html", "utf-8", null)
        }
    }

}
