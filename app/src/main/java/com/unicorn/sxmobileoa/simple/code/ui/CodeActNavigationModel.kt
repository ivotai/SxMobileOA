package com.unicorn.sxmobileoa.simple.code.ui

import dart.BindExtra
import dart.DartModel

@DartModel
class CodeActNavigationModel {

    @BindExtra
    lateinit var code: String

    @BindExtra
    lateinit var key: String

    @BindExtra
    lateinit var title: String

}