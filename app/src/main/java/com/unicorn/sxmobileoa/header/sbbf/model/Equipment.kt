package com.unicorn.sxmobileoa.header.sbbf.model

import android.support.annotation.IntRange
import com.unicorn.sxmobileoa.app.get
import com.unicorn.sxmobileoa.app.set
import com.unicorn.sxmobileoa.spd.model.Spd

class Equipment(val spd: Spd, @IntRange(from = 1, to = 5) val position: Int) {

    private val key_bfsbmc get() = "bfsbmc${position}_input"
    private val key_ppjxh get() = "ppjxh${position}_input"
    private val key_sl get() = "sl${position}_input"
    private val key_bfrq get() = "bfrq${position}_input"
    private val key_bfjg get() = "bfjg${position}_input"
    private val key_sfzx get() = "sfzx${position}_input"

    var bfsbmc
        get() = spd.get(key_bfsbmc)
        set(value) {
            spd.set(key_bfsbmc, value)
        }
    var ppjxh
        get() = spd.get(key_ppjxh)
        set(value) {
            spd.set(key_ppjxh, value)
        }
    var sl
        get() = spd.get(key_sl)
        set(value) {
            spd.set(key_sl, value)
        }
    var bfrq
        get() = spd.get(key_bfrq)
        set(value) {
            spd.set(key_bfrq, value)
        }
    var bfjg
        get() = spd.get(key_bfjg)
        set(value) {
            spd.set(key_bfjg, value)
        }
    var sfzx
        get() = spd.get(key_sfzx)
        set(value) {
            spd.set(key_sfzx, value)
        }

}