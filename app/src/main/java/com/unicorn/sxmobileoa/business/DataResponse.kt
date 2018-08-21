package com.unicorn.sxmobileoa.business

open  class DataResponse<T>(
        val msg: String,
        val data: Page<T>
)

