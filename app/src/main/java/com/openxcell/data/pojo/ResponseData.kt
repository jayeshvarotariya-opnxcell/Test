package com.openxcell.data.pojo

data class ResponseData<T>(

    var message: String? = null,
    var data: T? = null,
    var status: Int? = null,
    var key: String? = null

)