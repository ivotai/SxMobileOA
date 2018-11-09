package com.unicorn.sxmobileoa.n.ggxx.ui

import android.annotation.SuppressLint
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.n.ggxx.network.GetGgxxDetail
import com.unicorn.sxmobileoa.spd.model.Fj
import kotlinx.android.synthetic.main.title_webview.*

class GgxxDetailAct : BaseAct() {

    override val layoutId = R.layout.title_webview

    override fun initViews() {
        titleBar.setTitle("公告详情")
    }

    override fun bindIntent() {
        getDetail()
    }

    @SuppressLint("CheckResult")
    private fun getDetail() {
        // TODO GgxxHeaderView
        val id = intent.getStringExtra(Key.id)
        GetGgxxDetail(id).toMaybe(this).subscribe {
            it.filenameArr.zip(it.filepathArr) { a, b-> Fj(fjmc = a,fjdz = b) }
            //            wv.getSettings().setDefaultTextEncodingName(“UTF -8”) ;
            webView.loadData(it.content, "text/html", "UTF-8")
        }
    }

}
