package com.unicorn.sxmobileoa.n.news.ui

import android.annotation.SuppressLint
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.config.ConfigModule
import com.unicorn.sxmobileoa.app.ui.BaseAct
import kotlinx.android.synthetic.main.act_news_detail.*


class NewsDetailAct : BaseAct() {

    override val layoutId = R.layout.act_news_detail

    override fun initViews() {
        titleBar.setTitle("新闻详情")
    }

    override fun bindIntent() {
        getDetail()
    }

    @SuppressLint("CheckResult", "SetJavaScriptEnabled")
    private fun getDetail() {
        val contentId = intent.getStringExtra(Key.contentId)
        val url = "${ConfigModule.baseUrl}XwViewServlet.do?fybm=${Global.court!!.dm}&contentid=$contentId"
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }
        }
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(url)
//        webView.loadUrl("http://www.sxgaofa.cn")
//        webView.loadUrl("http://www.youdao.com/")
//            webView.loadUrl(url)
//        webView.webViewClient = object : WebViewClient() {
//            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
//                super.shouldOverrideUrlLoading(view, url)
//                return true
//            }
//        }
//        GetDetail(contentId).toMaybe(this).subscribe {
//            tvTitle2.text = it.title
//            tvDate.text = it.release_date.split(" ")[0]
//            val url = "http//192.168.20.100:8090/reqChaOne/XwViewServlet.do?fybm=R60&contentid=$contentId"
//            webView.webViewClient = object :WebViewClient(){
//                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
//                    view.loadUrl(url)
//                    return true
//                }
//            }

//            webView.loadUrl(url)

//            webView.loadDataWithBaseURL(url, null, "text/html", "utf-8", null)
//        }
    }

}
