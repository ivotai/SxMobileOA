package com.unicorn.sxmobileoa.business

open  class DataResponse<T>

{
      val msg: String = ""
      lateinit var  data: Page<T>
  }

