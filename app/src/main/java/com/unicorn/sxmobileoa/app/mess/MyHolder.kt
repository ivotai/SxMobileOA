package com.unicorn.sxmobileoa.app.mess

import android.view.View
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.extensions.LayoutContainer

class MyHolder(view: View) : BaseViewHolder(view), LayoutContainer {
    override val containerView: View = view
}