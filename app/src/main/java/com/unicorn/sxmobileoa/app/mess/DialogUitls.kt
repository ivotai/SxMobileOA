package com.unicorn.sxmobileoa.app.mess

import android.content.Context
import com.afollestad.materialdialogs.MaterialDialog

class DialogUitls{
    companion object {

        fun showMask(context: Context,title:String):MaterialDialog{
            return MaterialDialog.Builder(context)
                    .title(title)
                    .progress(true,100)
                    .show()

        }
    }
}