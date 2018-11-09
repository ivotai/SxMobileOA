package com.unicorn.sxmobileoa.n.news.ui

import android.annotation.SuppressLint
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.n.news.network.GetDetail
import kotlinx.android.synthetic.main.title_webview.*

class NewsDetailAct : BaseAct() {

    override val layoutId = R.layout.title_webview

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
            //            wv.getSettings().setDefaultTextEncodingName(“UTF -8”) ;

            webView.loadData(it.txt, "text/html", "UTF-8")
        }
    }

}
