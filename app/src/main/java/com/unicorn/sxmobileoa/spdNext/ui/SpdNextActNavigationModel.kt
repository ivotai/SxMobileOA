package com.unicorn.sxmobileoa.spdNext.ui

import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.unicorn.sxmobileoa.spd.model.Spd
import dart.BindExtra
import dart.DartModel

@DartModel
class SpdNextActNavigationModel {

    @BindExtra
    lateinit var menu: Menu

    @BindExtra
    lateinit var dbxx: Dbxx

    @BindExtra
    lateinit var spd: Spd

}