package com.unicorn.sxmobileoa.spd

import io.reactivex.Observable

class Editable() {

    fun couldEdit(nodeId: String) = Observable.fromArray("_SQR", "_NGR", "_QC", "_YBGS", "_LYR",
            "_TXJDSQ", "_BGSWS", "_NGRB", "_NBYJ", "_SFZBCSP"
            , "_CBQK", "_SWDJ", "_NGYJ")
            .any { nodeId.contains(it) }
}


