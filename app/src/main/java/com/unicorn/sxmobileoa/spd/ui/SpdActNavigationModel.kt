package com.unicorn.sxmobileoa.spd.ui

import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.simple.main.model.Menu
import dart.BindExtra
import dart.DartModel

@DartModel
class SpdActNavigationModel {

    @BindExtra
    lateinit var menu: Menu

    @BindExtra
    lateinit var dbxx: Dbxx

}