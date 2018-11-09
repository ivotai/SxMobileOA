package com.unicorn.sxmobileoa.n.news.ui

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.mess.MyHolder
import com.unicorn.sxmobileoa.n.news.model.News
import kotlinx.android.synthetic.main.item_text.*

class NewsAdapter : BaseQuickAdapter<News, MyHolder>(R.layout.item_text) {

    override fun convert(helper: MyHolder, item: News) {
        helper.apply {
            tvText.text = item.title
        }
    }

}