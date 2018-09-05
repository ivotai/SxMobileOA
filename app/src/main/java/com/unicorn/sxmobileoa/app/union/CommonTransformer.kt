package com.unicorn.sxmobileoa.app.union

import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CommonTransformer<Up>: SingleTransformer<Up, Up> {

    override fun apply(upstream: Single<Up>): SingleSource<Up> {
        return upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}