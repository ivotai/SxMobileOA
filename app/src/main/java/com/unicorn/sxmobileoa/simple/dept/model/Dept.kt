package com.unicorn.sxmobileoa.simple.dept.model

data class Dept(
        var text: String,
        val value: String
) {

    val isFather
        get() = value.length == 1

}