package com.unicorn.sxmobileoa.spd.model

import com.unicorn.sxmobileoa.simple.dbxx.model.Param
import com.unicorn.sxmobileoa.simple.main.model.Menu
import dart.BindExtra
import dart.DartModel

@DartModel
class SpdActNavigationModel {

    @BindExtra
    lateinit var menu: Menu

    @BindExtra
    lateinit var param: Param

}