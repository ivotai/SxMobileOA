package com.unicorn.sxmobileoa.simple.dept.model

data class Dept(
        var text: String,
        val id: String
) {

    val isFather
        get() = id.length == 1

}