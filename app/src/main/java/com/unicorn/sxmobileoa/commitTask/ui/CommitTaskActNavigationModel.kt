package com.unicorn.sxmobileoa.commitTask.ui

import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.model.SaveSpdResponse
import com.unicorn.sxmobileoa.spd.model.Spd
import dart.BindExtra
import dart.DartModel

@DartModel
class CommitTaskActNavigationModel {

    @BindExtra
    lateinit var menu: Menu

    @BindExtra
    lateinit var dbxx: Dbxx

    @BindExtra
    lateinit var spd: Spd

    @BindExtra
    lateinit var saveSpdResponse:SaveSpdResponse

}