package com.unicorn.sxmobileoa.sequenceFlow.model

import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.spd.model.Spd
import dart.BindExtra
import dart.DartModel

@DartModel
class SequenceFlowActNavigationModel {

//    @BindExtra
//    lateinit var menu: Menu

    @BindExtra
    lateinit var dbxx: Dbxx

    @BindExtra
    lateinit var spd: Spd

//    @BindExtra
//    lateinit var saveSpdResponse:SaveSpdResponse

}